package com.unosquare.amazon.restAssured.pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * Class to consult the api Employees
 */
public class EmployeePage extends BasePage {

    public EmployeePage() {
        super();
        RestAssured.baseURI = "http://dummy.restapiexample.com/";
        RestAssured.basePath = "/api/v1/";
    }

    /**
     * Method to get all the employess
     * @return a list of employees
     */
    public Response getAllEmployees() {
        return getResponse("employees");
    }

}
