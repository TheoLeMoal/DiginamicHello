package com.diginamic.Diginamic.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler({ Exception.class })
    protected ResponseEntity<String> errorTreatment(GestionExceptions exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}