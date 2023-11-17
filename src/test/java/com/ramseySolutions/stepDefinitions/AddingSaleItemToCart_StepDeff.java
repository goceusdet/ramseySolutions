package com.ramseySolutions.stepDefinitions;

import com.ramseySolutions.pages.CartPage;
import com.ramseySolutions.pages.HomePage;
import com.ramseySolutions.pages.SalePage;
import com.ramseySolutions.utils.API_Utils;
import com.ramseySolutions.utils.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AddingSaleItemToCart_StepDeff {


    Response response;
    String actualItemName;
    String actualPageTitle;
    String expectedItemName;
    String expectedPageTitle;
    String actualResponseMessage;
    HomePage homePage = new HomePage();
    SalePage salePage = new SalePage();
    CartPage cartPage = new CartPage();
    RequestSpecification reqSpecWithQueryParam;

    @Given("user is on the {string} page")
    public void user_is_on_the_page(String page) {
        expectedPageTitle = homePage.getPageTitleFromSheet(page);
        actualPageTitle = Driver.getDriver().getTitle();
        Assertions.assertTrue(actualPageTitle.contains(expectedPageTitle));
    }

    @When("user navigates to {string} page")
    public void user_navigates_to_page(String pageName) {
        homePage.navigateToPage(pageName);
    }

    @And("user should be able to see the Sale item list")
    public void user_should_be_able_to_see_the_sale_item_list(List<String> expectedList) {
        List<String> actualSaleItemsNames = salePage.getSaleItemsNamesList();
        Matchers.containsInAnyOrder(expectedList, actualSaleItemsNames);
    }

    @When("user clicks on {string} item")
    public void user_clicks_on_item(String itemName) {
        salePage.getSaleItem(itemName).click();
    }

    @When("user clicks Add to Cart button")
    public void user_clicks_add_to_cart_button() {
        salePage.clickOnAddToCartButton();
    }

    @And("user clicks on View Cart button")
    public void user_clicks_on_view_cart_button() {
        salePage.clickOnViewCartButton();
    }

    @Then("item name {string} should be displayed")
    public void item_name_should_be_displayed(String itemName) {
        Assertions.assertTrue(cartPage.getItemInCart(itemName).isDisplayed());
    }

    @And("quantity value for {string} should be {int}")
    public void quantity_should_be(String itemName, Integer expectedValue) {
        int actualQuantity = cartPage.getQuantityOfItemInCart(itemName);
        Assertions.assertEquals(expectedValue, actualQuantity);
    }

    @And("user deletes {string} from cart")
    public void user_deletes_from_cart(String itemName) {
        cartPage.deleteItemFromCart(itemName);
    }

    @Given("user adds {string} item to cart")
    public void user_adds_item_to_cart(String itemName) {
        salePage.addItemToCart(itemName);
    }

    @When("user clicks on View Cart")
    public void user_clicks_on_view_cart() {
        salePage.clickOnViewCartButton();
    }

    @Then("item {string} shouldn't be present in cart")
    public void item_shouldn_t_be_present_in_cart(String itemName) {
        System.out.println("Element presence in cart status = " + cartPage.getItemFromList(itemName));
        System.out.println("itemName = " + itemName);
        Assertions.assertNotEquals(cartPage.getItemFromList(itemName), itemName);
    }

    @When("user sends GET request to {string}")
    public void user_sends_get_request_to(String endpoint) {
        response = API_Utils.getAllSaleItemsWithReqResSpec(endpoint);
    }

    @Then("product list names from API response should match with UI product list names")
    public void product_list_names_from_api_response_should_match_with_ui_product_list_names(List<String> expectedUiList) {
        List<String> actualNamesList = response.path("data.site.product.name");
        Matchers.containsInAnyOrder(expectedUiList, actualNamesList);
    }

    @Then("user prepares API request with query param {string} as {string}")
    public void user_prepares_api_request_with_query_param_as(String queryParamKey, String queryParamValue) {
        reqSpecWithQueryParam = API_Utils.getReqSpecWithQueryParam(queryParamKey, queryParamValue);
    }

    @Then("user sends GET request to {string} endpoint")
    public void user_sends_get_request_to_endpoint(String endpoint) {
        response = reqSpecWithQueryParam.when()
                .get(endpoint).prettyPeek();
    }

    @Then("item name {string} on UI should be same with {string} value from API")
    public void item_name_on_ui_should_be_same_with_value_from_api(String itemName, String paramKey) {
        actualItemName = cartPage.getItemInCart(itemName).getText();
        expectedItemName = response.path("product." + paramKey);
        System.out.println("actualItemName = " + actualItemName);
        System.out.println("expectedItemName = " + expectedItemName);
        Assertions.assertEquals(expectedItemName, actualItemName);
    }

    @Then("user deletes {string} item from cart")
    public void user_deletes_item_from_cart(String itemName) {
        cartPage.deleteItemFromCart(itemName, 1);
    }

    @When("user decreases {string} item quantity value by {int}")
    public void user_decreases_quantity(String itemName, int quantity) {
       cartPage.deleteItemFromCart(itemName, quantity);
    }

    @Then("item {string} shouldn't be present in UI cart")
    public void item_shouldn_t_be_present_in_ui_cart(String itemName) {
        Assertions.assertNotEquals(cartPage.getItemFromList(itemName), itemName);
    }

    @And("response field {string} for deleted item should match {string}")
    public void response_Field_For_Deleted_Item_Should_Match(String responseKey, String responseValue) {
        actualResponseMessage = response.path(responseKey);
        Assertions.assertEquals(responseValue, actualResponseMessage);
    }

    @And("response status code should be {int}")
    public void response_Status_Code_Should_Be(int expectedStatusCode) {
        int actualStatusCode = response.statusCode();
        Assertions.assertEquals(expectedStatusCode, actualStatusCode);
    }
}
