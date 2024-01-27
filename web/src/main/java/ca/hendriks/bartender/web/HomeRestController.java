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

    @GetMapping
    public String directUserToHomePage(final Model model, @AuthenticationPrincipal final OidcUser principal) {
        addPrincipalToModelIfApplicable(model, principal);
        addApplicatioNameIntoModel(model);
        return goToHomePage();
    }

    private void addApplicatioNameIntoModel(final Model model) {
        model.addAttribute("appName", appName);
    }

    private void addPrincipalToModelIfApplicable(final Model model, final OidcUser principal) {
        if (principal != null) {
            model.addAttribute("profile", principal.getClaims());
        }
    }

    private String goToHomePage() {
        return "home";
    }

}
