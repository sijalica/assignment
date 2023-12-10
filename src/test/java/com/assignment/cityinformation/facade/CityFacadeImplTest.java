package com.assignment.cityinformation.facade;

import com.assignment.cityinformation.exception.CityCustomException;
import com.assignment.cityinformation.facade.impl.CityFacadeImpl;
import com.assignment.cityinformation.mapper.CityRequestMapper;
import com.assignment.cityinformation.mapper.CityResponseMapper;
import com.assignment.cityinformation.model.City;
import com.assignment.cityinformation.request.CityRequest;
import com.assignment.cityinformation.response.CityResponse;
import com.assignment.cityinformation.service.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CityFacadeImplTest {
    @Mock
    private CityService cityService;

    @Mock
    private CityRequestMapper cityRequestMapper;

    @Mock
    private CityResponseMapper cityResponseMapper;

    @Mock
    private WeatherRetrieval weatherRetrieval;

    @InjectMocks
    private CityFacadeImpl cityFacade;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveCity_NewCity_CitySaved() {
        // Arrange
        CityRequest cityRequest = new CityRequest();
        cityRequest.setName("NewCity");
        City city = new City();
        Mockito.when(cityService.getCityByName("NewCity")).thenReturn(Optional.empty());
        Mockito.when(cityRequestMapper.mapCity(cityRequest)).thenReturn(city);

        // Act
        cityFacade.saveCity(cityRequest);

        // Assert
        Mockito.verify(cityService, Mockito.times(1)).saveCity(city);
    }

    @Test
    public void saveCity_ExistingCity_ExceptionThrown() {
        // Arrange
        CityRequest cityRequest = new CityRequest();
        cityRequest.setName("ExistingCity");
        Mockito.when(cityService.getCityByName("ExistingCity")).thenReturn(Optional.of(new City()));

        // Act & Assert
        assertThrows(CityCustomException.class, () -> cityFacade.saveCity(cityRequest));
    }

    @Test
    public void getCity_ExistingCityName_CityResponseReturnedWithTemperature() {
        // Arrange
        String cityName = "TestCity";
        City city = new City();
        city.setName(cityName);
        Mockito.when(cityService.getCityByName(cityName)).thenReturn(Optional.of(city));

        // Mock the WebClient to return a temperature
        String temperature = "25°C";
        Mockito.when(weatherRetrieval.getCurrentTemperature(cityName)).thenReturn(temperature);

        // Act
        CityResponse cityResponse = new CityResponse();
        cityResponse.setName(cityName);
        cityResponse.setTempC(temperature);
        Mockito.when(cityResponseMapper.mapCity(city, "25°C")).thenReturn(cityResponse);
        CityResponse result = cityFacade.getCity(cityName);

        // Assert
        assertEquals(cityName, cityResponse.getName());
        assertEquals(temperature, result.getTempC());
    }

    @Test
    public void getCity_NonExistingCityName_ExceptionThrown() {
        // Arrange
        String cityName = "NonExistingCity";
        Mockito.when(cityService.getCityByName(cityName)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CityCustomException.class, () -> cityFacade.getCity(cityName));
    }

    @Test
    public void deleteCity_ExistingCityName_CityDeleted() {
        // Arrange
        String cityName = "TestCity";
        City city = new City();
        Mockito.when(cityService.getCityByName(cityName)).thenReturn(Optional.of(city));

        // Act
        cityFacade.deleteCity(cityName);

        // Assert
        Mockito.verify(cityService, Mockito.times(1)).deleteCity(city);
    }

    @Test
    public void deleteCity_NonExistingCityName_ExceptionThrown() {
        // Arrange
        String cityName = "NonExistingCity";
        Mockito.when(cityService.getCityByName(cityName)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CityCustomException.class, () -> cityFacade.deleteCity(cityName));
    }

    @Test
    public void updateCity_ExistingCityName_CityUpdated() {
        // Arrange
        String cityName = "TestCity";
        String population = "100000";
        City city = new City();
        Mockito.when(cityService.getCityByName(cityName)).thenReturn(Optional.of(city));

        // Act
        cityFacade.updateCity(cityName, population);

        // Assert
        assertEquals(population, city.getPopulation());
        Mockito.verify(cityService, Mockito.times(1)).saveCity(city);
    }

    @Test
    public void updateCity_NonExistingCityName_ExceptionThrown() {
        // Arrange
        String cityName = "NonExistingCity";
        String population = "100000";
        Mockito.when(cityService.getCityByName(cityName)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CityCustomException.class, () -> cityFacade.updateCity(cityName, population));
    }
}