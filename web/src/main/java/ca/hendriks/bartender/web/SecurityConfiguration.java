package ca.hendriks.bartender.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class DevSecurityConfiguration {

    @Value("${spring.application.authentication:true}")
    private boolean authentication;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        if (authentication) {
            http
                    .authorizeHttpRequests(authorize -> authorize
                            .requestMatchers("/", "/images/**").permitAll()
                            .anyRequest().authenticated()
                    )
                    .oauth2Login(withDefaults());
            return http.build();
        } else {
            http
                    .authorizeHttpRequests(authorize -> authorize
                            .requestMatchers("/", "/images/**").permitAll()
                            .anyRequest().permitAll()
                    )
                    .oauth2Login(withDefaults());
            return http.build();
        }
    }

}
