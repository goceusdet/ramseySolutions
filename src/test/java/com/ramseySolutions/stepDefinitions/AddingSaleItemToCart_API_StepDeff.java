package com.ramseySolutions.stepDefinitions;

import com.ramseySolutions.utils.API_Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class AddingSaleItemToCart_API_StepDeff {

    Response response;
    String actualMessage;
    Integer actualProductID;
    RequestSpecification allSaleItemsReqSpec;
    ResponseSpecification allSaleItemsRespSpec;

    @Given("I specify a request to retrieve all Sale items")
    public void iSpecifyARequestToRetrieveAllSaleItems() {
        allSaleItemsReqSpec = API_Utils.getBasicReqSpec();
    }

    @When("I specify a response to retrieve all Sale items")
    public void iSpecifyAGETResponseToRetrieveAllSaleItems() {
        allSaleItemsRespSpec = API_Utils.getBasicRespSpec();
    }

    @When("I send GET request to {string} endpoint")
    public void iSendGETRequestToEndpoint(String endpoint) {
        response = API_Utils.getAllSaleItemsWithReqResSpec(endpoint);
    }

    @Then("fields are not empty")
    public void fields_are_not_empty(List<String> listOfFields) {
        API_Utils.assertFieldsAreNotEmpty(listOfFields);
    }

    @Then("number of Sale items is {int}")
    public void number_of_Sale_items_is(int expectedListSize) {
        int actualListSize = API_Utils.getAllSaleItems().size();
        Assert.assertEquals(expectedListSize, actualListSize);
    }

    @Given("I send POST request to endpoint {string} for {string} item and {string} id")
    public void i_send_post_request_to_endpoint_for_item_and_id(String endpoint, String itemName, String productID) {
        response = API_Utils.postItemWithReqRespSpec(itemName, productID, endpoint);
        System.out.println("Item has been added to the cart");
    }

    @Then("field {string} is {string}")
    public void specified_field_is(String key, String expectedValue) {
        actualMessage = response.path("data." + key);
        Assert.assertEquals(expectedValue, actualMessage);
    }

    @Then("response field {string} should match {string}")
    public void response_field_should_match(String key, String expectedValue) {
        actualProductID = response.path("data.cart_item." + key);
        Assert.assertEquals(Integer.valueOf(expectedValue), actualProductID);
    }

    @Then("I delete the item {string} from cart")
    public void i_delete_the_item_from_cart(String productID) {
        response = API_Utils.deleteItemFromCartAPI(Integer.valueOf(productID));
        System.out.println("Item has been removed from the cart");
    }

    @Then("status code should be {int}")
    public void status_code_should_be(Integer expectedStatusCode) {
        int actualStatusCode = response.statusCode();
        Assert.assertEquals((int)expectedStatusCode, actualStatusCode);
    }

    @When("I search for {string} item from API {string} endpoint")
    public void i_search_for_item_from_api_endpoint(String queryParam, String endpoint) {
        response = API_Utils.searchForItemInCart(Integer.valueOf(queryParam), endpoint);
    }

    @And("GET response field {string} should match {string}")
    public void get_Response_Field_Should_Match(String key, String expectedValue) {
        actualMessage = response.path(key);
        Assert.assertEquals(expectedValue, actualMessage);
    }
}
