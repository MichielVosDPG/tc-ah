package com.ahold;

import com.ahold.exception.RijksmuseumTestException;
import com.ahold.model.collectiondetails.CollectionDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static com.ahold.helper.ApiHelper.delete;
import static com.ahold.helper.ApiHelper.get;
import static com.ahold.helper.ApiHelper.patch;
import static com.ahold.helper.ApiHelper.post;
import static com.ahold.helper.ApiHelper.put;
import static com.ahold.util.Properties.API_KEY;
import static com.ahold.util.Properties.URL_EN;

class CollectionDetailsTests {

    private static final Logger LOG = LoggerFactory.getLogger(CollectionDetailsTests.class);
    private final CollectionDetails collectionDetails;

    public CollectionDetailsTests() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            collectionDetails = objectMapper.readValue(new File("src/test/resources/CollectionDetails.json"), CollectionDetails.class);
        } catch (IOException e) {
            throw new RijksmuseumTestException("Error while reading collectionDetails.json file. With exception: %s.".formatted(e.getMessage()), e);
        }
    }

    @Test
    void validApiKeyShouldResultInStatusCode200() {
        LOG.info("Starting test: validApiKeyShouldResultInStatusCode200");
        String nachtwachtId = "SK-C-5";
        Assertions.assertEquals(200, get(getCollectionDetailsUrl(nachtwachtId), Map.of("key", API_KEY, "format", "json"), 200).statusCode());
    }

    @Test
    void invalidApiKeyShouldResultInStatusCode401() {
        String nachtwachtId = "SK-C-5";
        Assertions.assertEquals("\"Invalid key\"", get(getCollectionDetailsUrl(nachtwachtId), Map.of("key", "wrongApiKey", "format", "json"), 401).asPrettyString());
    }

    @Test
    void validCollectionDetailsShouldHaveArtObject() {
        String nachtwachtId = "SK-C-5";
        CollectionDetails collectionDetails = get(getCollectionDetailsUrl(nachtwachtId), Map.of("key", API_KEY, "format", "json"), 200).body().as(CollectionDetails.class);

        Assertions.assertNotNull(collectionDetails.artObject);
    }

    @Test
    void invalidCollectionDetailsShouldResultInStatusCode404() {
        String invalidId = "invalidId";
        Assertions.assertEquals(404, get(getCollectionDetailsUrl(invalidId), Map.of("key", API_KEY, "format", "json"), 404).statusCode());
    }

    @Test
    void itShouldNotBePossibleToDeleteCollectionDetails() {
        String nachtwachtId = "SK-C-5";
        Assertions.assertEquals(401, delete(getCollectionDetailsUrl(nachtwachtId)).statusCode());
    }

    @Test
    void itShouldNotBePossibleToPostCollectionDetails() {
        String nachtwachtId = "SK-C-5";
        Assertions.assertEquals(401, post(getCollectionDetailsUrl(nachtwachtId), collectionDetails).statusCode());
    }

    @Test
    void itShouldNotBePossibleToPutCollectionDetails() {
        String nachtwachtId = "SK-C-5";
        Assertions.assertEquals(401, put(getCollectionDetailsUrl(nachtwachtId), collectionDetails).statusCode());
    }

    @Test
    void itShouldNotBePossibleToPatchCollectionDetails() {
        String nachtwachtId = "SK-C-5";
        Assertions.assertEquals(401, patch(getCollectionDetailsUrl(nachtwachtId), collectionDetails).statusCode());
    }

    private String getCollectionDetailsUrl(String objectId) {
        return URL_EN + "/" + objectId;
    }


}
