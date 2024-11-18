package com.ahold;

import com.ahold.model.collection.Collection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static com.ahold.helper.ApiHelper.get;
import static com.ahold.util.Properties.API_KEY;
import static com.ahold.util.Properties.URL_NL;

class CollectionTests {



    @Test
    void validApiKeyShouldResultInStatusCode200() {
        Assertions.assertEquals(200, get(
                URL_NL,
                Map.of(
                        "key", API_KEY,
                        "format", "json"
                ),
                200).statusCode());
    }

    @Test
    void validCollectionShouldHaveArtObjects() {
        Collection collection = get(
                URL_NL,
                Map.of(
                        "key", API_KEY,
                        "format", "json"
                ),
                200
        ).body().as(Collection.class);

        Assertions.assertFalse(collection.artObjects.isEmpty());
    }

    @Test
    void wrongApiKeyShouldResultInStatusCode401() {

        Assertions.assertEquals("\"Invalid key\"", get(
                URL_NL,
                Map.of(
                        "key", "wrongApiKey",
                        "format", "json"
                ),
                401).asPrettyString());
    }

    @ParameterizedTest
    @CsvSource({
            "json, application/json; charset=utf-8",
            "jsonp, text/javascript; charset=utf-8",
            "xml, application/xml; charset=utf-8"
    })
    void validFormatsShouldBeRespected(String format, String expectedContentType) {
        Assertions.assertEquals(expectedContentType, get(
                URL_NL,
                Map.of(
                        "key", API_KEY,
                        "format", format
                ),
                200).contentType());
    }

    @Test
    void defaultFormatShouldBeJson() {
        Assertions.assertEquals("application/json; charset=utf-8", get(
                URL_NL,
                Map.of(
                        "key", API_KEY
                ),
                200).contentType());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "10", "100"})
    void validPageSizesShouldBeRespected(String pageSize) {
        Collection collection = get(
                URL_NL,
                Map.of(
                        "key", API_KEY,
                        "format", "json",
                        "ps", pageSize
                ),
                200
        ).body().as(Collection.class);

        Assertions.assertEquals(Integer.parseInt(pageSize), collection.artObjects.size());
    }

    @ParameterizedTest
    @CsvSource({
            "-1, 10",
            "0, 10",
            "101, 100"
    })
    void inValidPageSizesShouldBeRespected(String pageSize, String expectedPageSize) {
        Collection collection = get(
                URL_NL,
                Map.of(
                        "key", API_KEY,
                        "format", "json",
                        "ps", pageSize
                ),
                200
        ).body().as(Collection.class);

        Assertions.assertEquals(Integer.parseInt(expectedPageSize), collection.artObjects.size());
    }

    @ParameterizedTest
    @EmptySource
    void emptyPageSizeShouldReturnDefaultPageSize(String pageSize) {
        Collection collection = get(
                URL_NL,
                Map.of(
                        "key", API_KEY,
                        "format", "json",
                        "ps", pageSize
                ),
                200
        ).body().as(Collection.class);

        Assertions.assertEquals(10, collection.artObjects.size());
    }

    @ParameterizedTest
    @EmptySource
    void emptyPageShouldReturnFirstPage(String page) {
        Collection collection = get(
                URL_NL,
                Map.of(
                        "key", API_KEY,
                        "format", "json",
                        "p", page
                ),
                200
        ).body().as(Collection.class);

        Assertions.assertEquals(10, collection.artObjects.size());
    }

    @Test
    void artObjectShouldAlwaysHaveATitle() {
        Collection collection = get(
                URL_NL,
                Map.of(
                        "key", API_KEY,
                        "format", "json"
                ),
                200
        ).body().as(Collection.class);

        collection.artObjects.forEach(artObject -> Assertions.assertNotNull(artObject.title));
    }
}
