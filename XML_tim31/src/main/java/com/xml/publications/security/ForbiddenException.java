package com.xml.publications.security;;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException() { }

    public ForbiddenException(String message) {
        super(message);
    }
}