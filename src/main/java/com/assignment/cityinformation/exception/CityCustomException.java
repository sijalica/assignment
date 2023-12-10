package com.assignment.cityinformation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class CityCustomException extends RuntimeException {
    @NonNull
    private HttpStatus status;
    @NonNull
    private String message;
}
