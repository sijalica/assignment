package com.assignment.cityinformation.mapper;

import com.assignment.cityinformation.model.City;
import com.assignment.cityinformation.response.CityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CityResponseMapper {
    @Mapping(target = "tempC", source = "currentTempC")
    CityResponse mapCity(City city, String currentTempC);
}
