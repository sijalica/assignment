package com.assignment.cityinformation.service.impl;

import com.assignment.cityinformation.model.City;
import com.assignment.cityinformation.repository.CityRepository;
import com.assignment.cityinformation.service.CityService;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public void saveCity(City city) {
        cityRepository.save(city);
    }

    @Override
    public Optional<City> getCityByName(String name) {
        return cityRepository.findCityByName(name);
    }

    @Override
    public void deleteCity(City city) {
        cityRepository.delete(city);
    }
}
