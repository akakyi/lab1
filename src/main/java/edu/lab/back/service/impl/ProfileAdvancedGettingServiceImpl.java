package edu.lab.back.service.impl;

import edu.lab.back.db.dao.ProfileDao;
import edu.lab.back.db.entity.ProfileEntity;
import edu.lab.back.json.response.ProfileResponseJson;
import edu.lab.back.service.ProfileAdvancedGettingService;
import edu.lab.back.service.crud.implementations.BaseService;
import edu.lab.back.util.exception.InvalidPayloadException;
import lombok.NonNull;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ProfileAdvancedGettingServiceImpl extends BaseService implements ProfileAdvancedGettingService {

    private final ProfileDao profileDao;

    @Inject
    public ProfileAdvancedGettingServiceImpl(@NonNull final ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    @Override
    public List<ProfileResponseJson> getProfileBySchoolId(
        @NonNull final String schoolId
    ) throws InvalidPayloadException
    {
        final Long id = this.getId(schoolId);
        final List<ProfileEntity> profilesBySchoolId = this.profileDao.getProfilesBySchoolId(id);

        final List<ProfileResponseJson> result = profilesBySchoolId.stream()
            .map(ProfileResponseJson::convert)
            .collect(Collectors.toList());

        return result;
    }

}
