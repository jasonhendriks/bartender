package ca.hendriks.bartender.inventory;

import ca.hendriks.bartender.web.WebApplicationMain;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("ca/hendriks")
@CucumberContextConfiguration
@SpringBootTest(classes = {CucumberIT.Config.class, WebApplicationMain.class})
public class CucumberIT {

    @ComponentScan
    static class Config {

    }

}
