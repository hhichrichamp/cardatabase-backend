package com.champsoft.cardatabase.utilities;

public class InvalidOwnerDeletionException extends RuntimeException {
    public InvalidOwnerDeletionException(String message) {
        super(message);
    }
}