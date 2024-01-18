package ca.hendriks.bartender.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApplicationMain {

    private final static Logger LOGGER = LoggerFactory.getLogger(WebApplicationMain.class);

    public static void main(String[] args) {
        LOGGER.info("Starting");
        SpringApplication.run(WebApplicationMain.class);
        LOGGER.info("Finished");
    }

}
