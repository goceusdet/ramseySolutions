package com.ramseySolutions.stepDefinitions;

import com.ramseySolutions.utils.DB_Util;
import com.ramseySolutions.utils.Driver;
import com.ramseySolutions.utils.Environment;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.time.Duration;

public class Hooks {

    /**
     * @Before will be executed automatically before EVERY scenario in the project.
     */
    @Before("@ui")
    public void setupMethod(){
        System.out.println("this is coming from BEFORE");
        Driver.getDriver().get(Environment.URL);
        //Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Driver.getDriver().manage().window().maximize();
    }

    /**
     * @After will be executed automatically after EVERY scenario in the project.
     * @param scenario
     */
    @After("@ui")
    public void teardownMethod(Scenario scenario){

        if(scenario.isFailed()){
            byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        Driver.closeDriver();
    }

    /**
     * @Before method to set BaseURI for API connection.
     */
    @Before("@api")
    public void initApi() {
        RestAssured.baseURI = Environment.BASE_URL;
    }

    /**
     * @After method to Reset API connection
     */
    @After("@api")
    public void resetApi(){
        RestAssured.reset();
    }

    /**
     * @Before method to create connection with DB.
     */
    @Before("@db")
    public void setupDB(){
        DB_Util.createConnection();
        System.out.println("Successfully connected to Database");
    }

    /**
     * @After method to close connection with DB.
     */
    @After("@db")
    public void closeDB(){
        System.out.println("Closing DB connection...");
        DB_Util.destroy();
        System.out.println("Database connection has been closed.");
    }

}
