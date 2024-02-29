package ca.hendriks.bartender.web;

import ca.hendriks.bartender.web.functionaltest.BddMockMvcService;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("ca/hendriks")
@CucumberContextConfiguration
@WebAppConfiguration
@SpringBootTest(classes = {CucumberIT.Config.class, WebApplicationMain.class})
@ActiveProfiles("TEST")
public class CucumberIT {

    @ComponentScan
    static class Config {

        @Bean
        BddMockMvcService bddMockMvcService(final WebApplicationContext webApplicationContext) {
            return new BddMockMvcService(webApplicationContext);
        }

    }

}
