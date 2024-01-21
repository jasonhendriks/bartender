package ca.hendriks.bartender.web;

import ca.hendriks.bartender.web.functionaltest.BddMockMvcService;
import ca.hendriks.bartender.web.functionaltest.ingredient.BddIngredientService;
import ca.hendriks.bartender.web.functionaltest.recipe.BddRecipeService;
import ca.hendriks.bartender.web.inventory.IngredientsRestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("ca/hendriks")
@CucumberContextConfiguration
@WebAppConfiguration
@SpringBootTest(classes = {CucumberIT.Config.class, WebApplicationMain.class})
public class CucumberIT {

    @ComponentScan
    static class Config {

        @Bean
        BddMockMvcService bddMockMvcService(final WebApplicationContext webApplicationContext) {
            return new BddMockMvcService(webApplicationContext);
        }

        @Bean
        BddIngredientService bddIngredientService(final ObjectMapper objectMapper, final IngredientsRestController ingredientsRestController, final BddMockMvcService mockMvc) {
            return new BddIngredientService(objectMapper, mockMvc);
        }

        @Bean
        BddRecipeService bddRecipeService(){
            return new BddRecipeService();
        }

    }

}
