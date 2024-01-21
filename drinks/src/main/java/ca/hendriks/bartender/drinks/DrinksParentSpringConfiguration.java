package ca.hendriks.bartender.drinks;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(SpringJpaConfiguration.class)
public class DrinksParentSpringConfiguration {
}
