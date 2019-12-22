package edu.lab.back.service;

import edu.lab.back.json.response.ProfileResponseJson;
import edu.lab.back.util.exception.InvalidPayloadException;

import java.util.List;

public interface ProfileAdvancedGettingService {

    List<ProfileResponseJson> getProfileBySchoolId(String schoolId) throws InvalidPayloadException;

}
