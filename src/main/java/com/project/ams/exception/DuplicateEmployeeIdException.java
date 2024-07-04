package com.project.ams.exception;

public class DuplicateEmployeeIdException extends Exception {
    public DuplicateEmployeeIdException(String message) {
        super(message);
    }
}