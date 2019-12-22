package edu.lab.back.service.crud.implementations;

import edu.lab.back.util.ValidationMessages;
import edu.lab.back.util.exception.InvalidPayloadException;
import lombok.NonNull;

public abstract class BaseService {

    protected Long getId(@NonNull final String idString) throws InvalidPayloadException {
        try {
            final long id = Long.parseLong(idString);
            return id;
        } catch (NumberFormatException e) {
            throw new InvalidPayloadException(ValidationMessages.INVALID_PATH_VARIABLE);
        }
    }

    protected Integer getIntId(@NonNull final String idString) throws InvalidPayloadException {
        try {
            final int id = Integer.parseInt(idString);
            return id;
        } catch (NumberFormatException e) {
            throw new InvalidPayloadException(ValidationMessages.INVALID_PATH_VARIABLE);
        }
    }

}
