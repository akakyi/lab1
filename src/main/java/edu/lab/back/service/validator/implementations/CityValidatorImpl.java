package edu.lab.back.service.validator.implementations;

import edu.lab.back.json.request.CityRequestJson;
import edu.lab.back.service.validator.CityValidator;
import lombok.NonNull;

import javax.ejb.Stateless;

@Stateless
public class CityValidatorImpl implements CityValidator {

    @Override
    public boolean validateCitySave(final CityRequestJson requestJson) {
        if (requestJson == null) {
            return false;
        }
        if (requestJson.getId() != null) {
            return false;
        }

        return this.baseValidation(requestJson);
    }

    @Override
    public boolean validateCityUpdate(final CityRequestJson requestJson) {
        if (requestJson == null) {
            return false;
        }
        if (requestJson.getId() == null) {
            return false;
        }

        return this.baseValidation(requestJson);
    }

    private boolean baseValidation(@NonNull final CityRequestJson requestJson) {
        if (requestJson.getName() == null) {
            return false;
        } else {
            if (requestJson.getName().equals("")) {
                return false;
            }
        }

        return true;
    }
}
