package edu.lab.back.util.exception;

import lombok.NonNull;

public class ResourceNotFound extends Exception {

    public ResourceNotFound(@NonNull final String message) {
        super(message);
    }

}
