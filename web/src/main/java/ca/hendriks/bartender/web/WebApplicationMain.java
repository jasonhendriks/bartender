package ca.hendriks.bartender.web;

import ca.hendriks.bartender.drinks.DrinksParentSpringConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(DrinksParentSpringConfiguration.class)
public class WebApplicationMain {

    public static void main(String[] args) {
        SpringApplication.run(WebApplicationMain.class);
    }

}
