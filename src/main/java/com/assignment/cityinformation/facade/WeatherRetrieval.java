package com.assignment.cityinformation.facade;

import com.assignment.cityinformation.exception.CityCustomException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WeatherRetrieval {
    @Value("${base.url}")
    private String baseUrl;

    @Value("${key}")
    private String key;
    public String getCurrentTemperature(String cityName) {
        WebClient webClient = WebClient
                .builder()
                .baseUrl(baseUrl)
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("key", key)
                .build();


        String response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/current.json")
                        .queryParam("q", cityName)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();


        try {
            JSONObject object = new JSONObject(response);
            String current_object = object.getString("current");
            JSONObject temp_object = new JSONObject(current_object);

            return temp_object.getString("temp_c");

        } catch (JSONException e) {
            throw new CityCustomException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
