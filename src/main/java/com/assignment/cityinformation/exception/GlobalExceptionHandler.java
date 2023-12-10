package com.assignment.cityinformation.exception;

import com.assignment.cityinformation.response.error.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CityCustomException.class)
    public ResponseEntity<ErrorResponse> errorResponse(CityCustomException cityCustomException) {
        return new ResponseEntity<>(new ErrorResponse(cityCustomException.getStatus(), cityCustomException.getMessage()), cityCustomException.getStatus());
    }
}
