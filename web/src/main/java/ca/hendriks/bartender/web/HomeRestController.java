package ca.hendriks.bartender.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeRestController {

    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String devHomePage(final Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

}
