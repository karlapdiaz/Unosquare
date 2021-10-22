package com.unosquare.amazon.restAssured.pages;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * Class to implement all request types
 */
public class BasePage implements PageInterface {

    /**
     * Contructor of the class
     */
    public BasePage() {
        RestAssured.baseURI = "";
        RestAssured.basePath = "";
    }

    /**
     * Method to get the response of url
     * @param getPath
     * @return response
     */
    public Response getResponse(String getPath) {
        return given()
                .header("Accept","application/json" )
                .header("Content-type", "application/json")
                .when()
                .get(getPath)
                .then()
                .and()
                .log()
                .body()
                .extract()
                .response();
    }

    /**
     * Method to send information
     * @param token
     * @param postPath
     * @param body
     * @return response
     */
    public Response postResponse(String token, String postPath,String body) {
        return given()
                .header("Accept","application/json" )
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer "+token)
                .log()
                .body()
                .contentType(ContentType.JSON)
                .body(body)
                .post(postPath)
                .then()
                .log()
                .body()
                .extract()
                .response();
    }

    /**
     * Method to insert information
     * @param token
     * @param postPath
     * @param body
     * @return response
     */
    public Response putResponse(String token, String postPath,String body) {
        return given()
                .header("Accept","application/json" )
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer "+token)
                .body(body)
                .log()
                .body()
                .when()
                .put(postPath)
                .then()
                .log()
                .body()
                .extract()
                .response();
    }

    /**
     * Method to update
     * @param token
     * @param postPath
     * @param body
     * @return response
     */
    public Response patchResponse(String token, String postPath,String body) {
        return given()
                .header("Accept","application/json" )
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer "+token)
                .and()
                .body(body)
                .log()
                .body()
                .when()
                .patch(postPath)
                .then()
                .log()
                .body()
                .extract()
                .response();
    }

    /**
     * Method to delete information
     * @param token
     * @param postPath
     * @param body
     * @return response
     */
    public Response deleteResponse(String token, String postPath,String body) {
        return given()
                .header("Accept","application/json" )
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer "+token)
                .log()
                .body()
                .when()
                .delete(postPath+ "/" + body)
                .then()
                .log()
                .body()
                .extract()
                .response();
    }
}