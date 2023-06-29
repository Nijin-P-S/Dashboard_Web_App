package com.nijin.customer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateResourceException extends Exception{
    DuplicateResourceException(String message){
        super(message);
    }
}
