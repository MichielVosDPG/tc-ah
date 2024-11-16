package com.ahold.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CountFacets {

    @JsonProperty("hasimage")
    public Integer hasImage;
    @JsonProperty("ondisplay")
    public Integer onDisplay;
}
