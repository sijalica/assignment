package com.assignment.cityinformation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityRequest {
    private String name;
    private String country;
    @JsonProperty("state/region")
    private String region;
    private String population;

}
