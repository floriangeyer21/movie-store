package com.movie.store.exceptions;

public class NoAppropriateHashingAlgorithmException extends RuntimeException {
    public NoAppropriateHashingAlgorithmException(String message, Throwable cause) {
        super(message, cause);
    }
}
