package com.ramseySolutions.utils;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class API_Utils {


    public static ResponseSpecification postItemRespSpec(){
        return expect().statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON);
    }
    public static ResponseSpecification getBasicRespSpec() {
        return expect().statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON);
    }
    public static Response deleteItemFromCartAPI(Integer productID){
        return given().
                queryParam("product_id", productID).
                when().
                delete("/remove").
                then()
                .statusCode(HttpStatus.SC_NO_CONTENT).extract().response();
    }
    public static List<Map<String, Object>> getAllSaleItems() {
        return given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .expect()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .when()
                .get(Environment.BASE_URL + "/graphql").path("data.site.product");
    }
    public static void assertFieldsAreNotEmpty(List<String> fieldList) {
        List<Map<String, Object>> allSaleItemsListMap = getAllSaleItems();
        for (int i = 0; i < allSaleItemsListMap.size(); i++){
            System.out.println("getAllSaleItems().get(i) = " + allSaleItemsListMap.get(i));
            for (String eachFieldName : fieldList){
                System.out.println("getAllSaleItems().get(i).get(eachFieldName) = " + allSaleItemsListMap.get(i).get(eachFieldName));
                MatcherAssert.assertThat(allSaleItemsListMap.get(i).get(eachFieldName), Matchers.notNullValue());
            }
        }
    }
    public static Response getAllSaleItemsWithReqResSpec(String endpoint) {
        return given().
                spec(getBasicReqSpec())
                .when()
                .get(endpoint).prettyPeek()
                .then()
                .spec(getBasicRespSpec()).extract().response();
    }
    public static RequestSpecification postItemReqSpec(String itemName, Integer productID){
        Map<String, Object> body = new HashMap<>();
        body.put("name", itemName);
        body.put("product_id", productID);
        return given().
                spec(getBasicReqSpec()).
                contentType(ContentType.JSON).
                body(body);
    }
    public static RequestSpecification getBasicReqSpec() {
        return given().accept(ContentType.JSON);
    }
    public static Response postItemWithReqRespSpec(String itemName, String productID, String endpoint){
        return given()
                .spec(postItemReqSpec(itemName, Integer.valueOf(productID)))
                .when().post(endpoint).prettyPeek()
                .then().spec(postItemRespSpec()).extract().response();
    }
    public static Response searchForItemInCart(Integer queryParam, String endpoint){
        return getBasicReqSpec()
                .queryParam("product_id",queryParam)
                .get(endpoint).prettyPeek();
    }
    public static RequestSpecification getReqSpecWithQueryParam(String queryParamKey, String queryParamValue){
        return given()
                .spec(getBasicReqSpec())
                .queryParam(queryParamKey, queryParamValue);
    }


}