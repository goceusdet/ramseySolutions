package com.ramseySolutions.stepDefinitions;

import com.ramseySolutions.utils.Environment;
import io.cucumber.java.en.Given;

public class EnvironmentStep {
    // This is running to confirm on which environment url UI is running and what is the base url that is set up for API test.
    @Given("I get related environment information")
    public void i_get_related_environment_information() {
        System.out.println("Environment.URL = " + Environment.URL);
        System.out.println("Environment.BASE_URL = " + Environment.BASE_URL);
    }
}
