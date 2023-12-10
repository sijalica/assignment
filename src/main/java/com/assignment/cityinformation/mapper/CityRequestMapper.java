package com.assignment.cityinformation.mapper;

import com.assignment.cityinformation.model.City;
import com.assignment.cityinformation.request.CityRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CityRequestMapper {
    @Mapping(target = "stateRegion", source = "region")
    City mapCity(CityRequest cityRequest);
}
