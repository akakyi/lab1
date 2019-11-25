package edu.lab.back.service.impl;

import edu.lab.back.db.dao.ProfileDao;
import edu.lab.back.db.entity.ProfileEntity;
import edu.lab.back.json.response.ProfileResponseJson;
import edu.lab.back.service.ProfileAdvancedGettingService;
import edu.lab.back.service.crud.implementations.BaseService;
import edu.lab.back.util.ValidationMessages;
import edu.lab.back.util.exception.InvalidPayloadException;
import edu.lab.back.util.exception.ResourceNotFound;
import lombok.NonNull;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ProfileAdvancedGettingServiceImpl extends BaseService implements ProfileAdvancedGettingService {

    private final ProfileDao profileDao;

//    private final SchoolDao schoolDao;

    @Inject
    public ProfileAdvancedGettingServiceImpl(@NonNull final ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    @Override
    public List<ProfileResponseJson> getProfileBySchoolId(
        @NonNull final String schoolId
    ) throws InvalidPayloadException, ResourceNotFound {
        final Long id = this.getId(schoolId);
//        final SchoolEntity school = this.schoolDao.getById(id, SchoolEntity.class);
//        final List<ProfileEntity> profiles = school.getProfiles();
//        return profiles.stream().map(ProfileResponseJson::convert).collect(Collectors.toList());

        final List<ProfileEntity> profilesBySchoolId = this.profileDao.getProfilesBySchoolId(id);
        if (profilesBySchoolId.isEmpty()) {
            throw new ResourceNotFound(ValidationMessages.RESOURCE_NOT_FOUND);
        }

        final List<ProfileResponseJson> result = profilesBySchoolId.stream()
            .map(ProfileResponseJson::convert)
            .collect(Collectors.toList());

        return result;
    }

}
