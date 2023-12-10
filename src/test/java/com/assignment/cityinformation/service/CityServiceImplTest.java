package com.assignment.cityinformation.service;

import com.assignment.cityinformation.model.City;
import com.assignment.cityinformation.repository.CityRepository;
import com.assignment.cityinformation.service.impl.CityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

public class CityServiceImplTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityServiceImpl cityService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

        @Test
        public void testSaveCity() {
            City city = new City();
            city.setName("TestCity");
            cityService.saveCity(city);
            Mockito.verify(cityRepository, Mockito.times(1)).save(city);
        }

        @Test
        public void testGetCityByName() {
            String cityName = "TestCity";
            City city = new City();
            city.setName(cityName);
            Mockito.when(cityRepository.findCityByName(cityName)).thenReturn(Optional.of(city));

            Optional<City> returnedCity = cityService.getCityByName(cityName);
            assert(returnedCity.isPresent());
            assert(returnedCity.get().getName().equals(cityName));
        }

    @Test
    public void testDeleteCity() {
        City city = new City();
        city.setName("TestCity");
        cityService.deleteCity(city);
        Mockito.verify(cityRepository, Mockito.times(1)).delete(city);
    }
}