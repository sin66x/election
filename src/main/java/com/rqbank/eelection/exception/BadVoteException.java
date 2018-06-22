package com.rqbank.eelection.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BadVoteException extends RuntimeException {
    public BadVoteException(){
        super("You dont have permission for this vote...");
    }

}
