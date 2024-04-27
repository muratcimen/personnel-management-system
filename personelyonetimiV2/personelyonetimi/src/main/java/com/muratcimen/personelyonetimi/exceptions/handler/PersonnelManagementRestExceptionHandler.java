package com.muratcimen.personelyonetimi.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

public class PersonnelManagementRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<PersonnelManagementErrorResponse> handleException(PersonnelManagementNotFoundException exc) {

        //create a PersonnelManagementErrorResponse

        PersonnelManagementErrorResponse error = new PersonnelManagementErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        //return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<PersonnelManagementErrorResponse> handleException(ResponseStatusException exc) {

        //create a PersonnelManagementErrorResponse

        PersonnelManagementErrorResponse error = new PersonnelManagementErrorResponse();

        error.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        error.setMessage("The request rate is limited! Please try again later.");
        error.setTimeStamp(System.currentTimeMillis());

        //return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.TOO_MANY_REQUESTS);
    }
}
