package edu.lab.back.service;

import edu.lab.back.json.response.SchoolResponseJson;
import edu.lab.back.util.exception.InvalidPayloadException;

import java.util.List;

public interface SchoolAdvancedGettingService {

    List<SchoolResponseJson> getSchoolsByCityId(String cityId) throws InvalidPayloadException;

}
