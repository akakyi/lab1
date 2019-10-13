package edu.lab.back.service.validator;

import edu.lab.back.json.request.CityRequestJson;

public interface CityValidator {

    boolean validateCitySave(CityRequestJson requestJson);

    boolean validateCityUpdate(CityRequestJson requestJson);

}
