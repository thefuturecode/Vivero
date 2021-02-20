package cl.ubb.scrumitos.integrationTests;

import org.springframework.boot.test.context.SpringBootTest;

import cl.ubb.scrumitos.ViveroApplication;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = ViveroApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberSpringContextConfiguration {

}
