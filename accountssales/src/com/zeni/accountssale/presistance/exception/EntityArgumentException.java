package com.zeni.accountssale.presistance.exception;

public class EntityArgumentException extends RuntimeException {
    
    public EntityArgumentException(String message) {
        super(message);
    }
    
    public EntityArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}