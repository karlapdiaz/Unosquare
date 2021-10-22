package com.unosquare.amazon.restAssured.pages;

import io.restassured.response.Response;

/**
 * Interface with the generic http method of a API
 */
public interface PageInterface {

    public Response getResponse(String getPath);

    public Response postResponse(String token, String postPath,String body);

    public Response putResponse(String token, String postPath,String body);

    public Response patchResponse(String token, String postPath,String body);

    public Response deleteResponse(String token, String postPath,String body);
}
