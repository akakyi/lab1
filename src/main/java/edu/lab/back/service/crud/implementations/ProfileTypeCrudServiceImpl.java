package edu.lab.back.service.crud.implementations;

import edu.lab.back.db.dao.ProfileTypeDao;
import edu.lab.back.db.entity.ProfileTypeEntity;
import edu.lab.back.json.ProfileTypeJson;
import edu.lab.back.service.crud.ProfileTypeCrudService;
import edu.lab.back.util.ValidationMessages;
import edu.lab.back.util.exception.InvalidPayloadException;
import edu.lab.back.util.exception.ResourceNotFound;
import lombok.NonNull;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Transactional(rollbackOn = Exception.class)
public class ProfileTypeCrudServiceImpl extends BaseService implements ProfileTypeCrudService {

    private final ProfileTypeDao profileTypeDao;

    @Inject
    public ProfileTypeCrudServiceImpl(@NonNull final ProfileTypeDao profileTypeDao) {
        this.profileTypeDao = profileTypeDao;
    }

    @Override
    public ProfileTypeJson getById(final String idString) throws InvalidPayloadException, ResourceNotFound {
        final Integer id = this.getIntId(idString);
        final ProfileTypeEntity profileType = this.profileTypeDao.getById(id);
        if (profileType == null) {
            throw new ResourceNotFound(ValidationMessages.RESOURCE_NOT_FOUND);
        }

        final ProfileTypeJson result = ProfileTypeJson.convert(profileType);

        return result;
    }

    @Override
    public List<ProfileTypeJson> getAll() {
        final List<ProfileTypeEntity> types = this.profileTypeDao.getAll();
        final List<ProfileTypeJson> result = types.stream()
            .map(ProfileTypeJson::convert)
            .collect(Collectors.toList());

        return result;
    }

    @Override
    public ProfileTypeJson deleteById(final String idString) throws InvalidPayloadException, ResourceNotFound {
        final Integer id = this.getIntId(idString);
        final ProfileTypeEntity deleted = this.profileTypeDao.deleteById(id);
        if (deleted == null) {
            throw new ResourceNotFound(ValidationMessages.RESOURCE_NOT_FOUND);
        }

        final ProfileTypeJson result = ProfileTypeJson.convert(deleted);
        return result;
    }

    @Override
    public ProfileTypeJson save(@NonNull final ProfileTypeJson cityJson) {
        final ProfileTypeEntity entity = ProfileTypeEntity.convert(cityJson);
        final ProfileTypeEntity saved = this.profileTypeDao.save(entity);
        final ProfileTypeJson result = ProfileTypeJson.convert(saved);

        return result;
    }

    @Override
    public ProfileTypeJson update(@NonNull final ProfileTypeJson cityJson) throws ResourceNotFound {
        final Integer id = cityJson.getId();
        final ProfileTypeEntity entity = this.profileTypeDao.getById(id);
        if (entity == null) {
            throw new ResourceNotFound(ValidationMessages.RESOURCE_NOT_FOUND);
        }
        entity.setName(cityJson.getName());
        final ProfileTypeEntity updated = this.profileTypeDao.update(entity);
        final ProfileTypeJson result = ProfileTypeJson.convert(updated);

        return result;
    }
}
