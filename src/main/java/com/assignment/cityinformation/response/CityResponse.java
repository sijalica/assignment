package com.assignment.cityinformation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityResponse {
    private long id;
    private String name;
    private String country;
    @JsonProperty("state/region")
    private String stateRegion;
    private String population;
    private String tempC;
}
