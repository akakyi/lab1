package edu.lab.back.service.crud.implementations;

import lombok.NonNull;

public abstract class BaseCrudService {

    protected Long getId(@NonNull final String idString) {
        final long id = Long.parseLong(idString);
        return id;
    }

}
