package com.movie.store.exceptions;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(Throwable throwable) {
        super(throwable);
    }

    public AuthenticationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
