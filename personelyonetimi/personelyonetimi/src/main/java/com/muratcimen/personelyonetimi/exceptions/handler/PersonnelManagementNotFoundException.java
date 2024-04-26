package com.muratcimen.personelyonetimi.exceptions.handler;

public class PersonnelManagementNotFoundException extends RuntimeException{
    public PersonnelManagementNotFoundException(String message) {
        super(message);
    }

    public PersonnelManagementNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonnelManagementNotFoundException(Throwable cause) {
        super(cause);
    }
}
