package ca.hendriks.bartender.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeRestController {

    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String home(final Model model, @AuthenticationPrincipal final OidcUser principal) {
        model.addAttribute("appName", appName);
        return "home";
    }

}
