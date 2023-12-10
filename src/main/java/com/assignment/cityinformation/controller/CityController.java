package com.assignment.cityinformation.controller;

import com.assignment.cityinformation.facade.CityFacade;
import com.assignment.cityinformation.request.CityRequest;
import com.assignment.cityinformation.response.CityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("city")
@RequiredArgsConstructor
public class CityController {
    private final CityFacade cityFacade;

    @PostMapping("/saveCity")
    public String save(@RequestBody CityRequest cityRequest) {
        cityFacade.saveCity(cityRequest);
        return "Success";
    }

    @GetMapping("/getCity")
    @ResponseBody
    public ResponseEntity<CityResponse> get(@RequestParam String cityName) {
        CityResponse cityResponse = cityFacade.getCity(cityName);
        return new ResponseEntity<>(cityResponse, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCity")
    public ResponseEntity<String> delete(@RequestParam String cityName) {
        cityFacade.deleteCity(cityName);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @PatchMapping("/updateCity")
    public ResponseEntity<String> update(@RequestParam String cityName, @RequestParam String cityPopulation) {
        cityFacade.updateCity(cityName, cityPopulation);
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }
}
