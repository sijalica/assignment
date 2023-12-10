package com.assignment.cityinformation.facade;

import com.assignment.cityinformation.request.CityRequest;
import com.assignment.cityinformation.response.CityResponse;

public interface CityFacade {
    void saveCity(CityRequest cityRequest);

    CityResponse getCity(String cityName);

    void deleteCity(String cityName);

    void updateCity(String cityName, String population);
}
