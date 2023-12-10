package com.assignment.cityinformation.service;

import com.assignment.cityinformation.model.City;

import java.util.Optional;

public interface CityService {
    void saveCity(City city);

    Optional<City> getCityByName(String name);

    void deleteCity(City city);
}
