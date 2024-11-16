package com.ahold;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

class CollectionTests {

    @Test
    void myFirstTest() {
        RestAssured.given()
                .baseUri("https://www.rijksmuseum.nl/api/nl/collection")
                .queryParam("key", "")
                .queryParam("format", "json")
                .queryParam("q", "Rembrandt")
                .when()
                .get()
                .then()
                .log()
                .all()
                .statusCode(200);
    }
}
