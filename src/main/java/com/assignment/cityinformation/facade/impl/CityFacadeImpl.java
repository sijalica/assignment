package com.assignment.cityinformation.facade.impl;

import com.assignment.cityinformation.exception.CityCustomException;
import com.assignment.cityinformation.facade.CityFacade;
import com.assignment.cityinformation.facade.WeatherRetrieval;
import com.assignment.cityinformation.mapper.CityRequestMapper;
import com.assignment.cityinformation.mapper.CityResponseMapper;
import com.assignment.cityinformation.model.City;
import com.assignment.cityinformation.request.CityRequest;
import com.assignment.cityinformation.response.CityResponse;
import com.assignment.cityinformation.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class CityFacadeImpl implements CityFacade {
    private final CityService cityService;
    private final CityRequestMapper cityRequestMapper;
    private final CityResponseMapper cityResponseMapper;
    private final WeatherRetrieval weatherRetrieval;

    @Override
    public void saveCity(CityRequest cityRequest) {
        log.info("Checking if " + cityRequest.getName() + "exists");
        Optional<City> cityOptional = cityService.getCityByName(cityRequest.getName());

        if (cityOptional.isPresent()) {
            throw new CityCustomException(HttpStatus.FOUND, "City already exists!");
        }

        City city = cityRequestMapper.mapCity(cityRequest);
        cityService.saveCity(city);

        log.info("Saved: " + cityRequest);
    }

    @Override
    public CityResponse getCity(String cityName) {
        log.info("Checking if " + cityName + "exists");
        City city = cityService.getCityByName(cityName).orElseThrow(() -> new CityCustomException(HttpStatus.NOT_FOUND ,"City not found!"));

        String tempC = weatherRetrieval.getCurrentTemperature(cityName);

        return cityResponseMapper.mapCity(city, tempC);
    }

    @Override
    public void deleteCity(String cityName) {
        log.info("Checking if " + cityName + "exists");
        City city = cityService.getCityByName(cityName).orElseThrow(() -> new CityCustomException(HttpStatus.NOT_FOUND ,"City not found!"));

        cityService.deleteCity(city);
    }

    @Override
    public void updateCity(String cityName, String population) {
        log.info("Checking if " + cityName + "exists");
        City city = cityService.getCityByName(cityName).orElseThrow(() -> new CityCustomException(HttpStatus.NOT_FOUND ,"City not found!"));

        city.setPopulation(population);

        log.info("Population is set to: " + population);

        cityService.saveCity(city);
    }
}
