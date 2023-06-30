package com.nijin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateResourceException extends Exception{
    public DuplicateResourceException(String message){
        super(message);
    }
}
