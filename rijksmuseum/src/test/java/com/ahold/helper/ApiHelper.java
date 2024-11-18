package com.ahold.helper;

import com.ahold.exception.RijksmuseumTestException;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public final class ApiHelper {

    private ApiHelper() {}

        public static Response get(String url, Map<String, String> queryParams, int expectedStatusCode) {
            try {
                return RestAssured.given()
                        .baseUri(url)
                        .queryParams(queryParams)
                        .when()
                        .get()
                        .then()
                        .statusCode(expectedStatusCode)
                        .log()
                        .all()
                        .extract()
                        .response();
            } catch (Exception e) {
                throw new RijksmuseumTestException("Error while making GET request to: %s. With exception: %s.".formatted(url, e.getMessage()), e);
            }
        }

        public static Response post(String url, Object body) {
            try {
                return RestAssured.given()
                        .baseUri(url)
                        .body(body)
                        .when()
                        .post()
                        .then()
                        .log()
                        .all()
                        .extract()
                        .response();
            } catch (Exception e) {
                throw new RijksmuseumTestException("Error while making POST request to: %s. With exception: %s.".formatted(url, e.getMessage()), e);
            }
        }

    public static Response put(String url, Object body) {
        try {
            return RestAssured.given()
                    .baseUri(url)
                    .body(body)
                    .when()
                    .put()
                    .then()
                    .log()
                    .all()
                    .extract()
                    .response();
        } catch (Exception e) {
            throw new RijksmuseumTestException("Error while making POST request to: %s. With exception: %s.".formatted(url, e.getMessage()), e);
        }
    }

    public static Response patch(String url, Object body) {
        try {
            return RestAssured.given()
                    .baseUri(url)
                    .body(body)
                    .when()
                    .patch()
                    .then()
                    .log()
                    .all()
                    .extract()
                    .response();
        } catch (Exception e) {
            throw new RijksmuseumTestException("Error while making POST request to: %s. With exception: %s.".formatted(url, e.getMessage()), e);
        }
    }

    public static Response delete(String url) {
        try {
            return RestAssured.given()
                    .baseUri(url)
                    .when()
                    .delete()
                    .then()
                    .log()
                    .all()
                    .extract()
                    .response();
        } catch (Exception e) {
            throw new RijksmuseumTestException("Error while making DELETE request to: %s. With exception: %s.".formatted(url, e.getMessage()), e);
        }
    }

}
