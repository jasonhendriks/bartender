package ca.hendriks.bartender.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Value("${spring.application.authentication:true}")
    private boolean authenticationIsEnabled;

    @Value("${okta.oauth2.issuer}")
    private String issuer;

    @Value("${okta.oauth2.client-id}")
    private String clientId;

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorize -> configureHttpRequests(authorize))
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
                .oauth2Login(withDefaults())
                .logout(logout -> logout.addLogoutHandler(configureLogout()))
                .build();
    }

    private void configureHttpRequests(final AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorize) {
        final AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry builder = authorize
                .requestMatchers("/", "/images/**").permitAll();
        if (authenticationIsEnabled)
            builder.anyRequest().permitAll();
        else
            builder.anyRequest().permitAll();
    }

    private LogoutHandler configureLogout() {
        return (request, response, authentication) -> {
            try {
                String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
                response.sendRedirect(issuer + "v2/logout?client_id=" + clientId + "&returnTo=" + baseUrl);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

}
