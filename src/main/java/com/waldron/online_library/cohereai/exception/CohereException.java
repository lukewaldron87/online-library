package com.waldron.online_library.cohereai.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CohereException extends RuntimeException{

    public CohereException(String message){
        super(message);
    }
}
