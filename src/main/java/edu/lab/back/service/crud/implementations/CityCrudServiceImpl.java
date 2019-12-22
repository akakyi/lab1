package edu.lab.back.service.crud.implementations;

import edu.lab.back.db.dao.CityDao;
import edu.lab.back.db.entity.CityEntity;
import edu.lab.back.json.request.CityRequestJson;
import edu.lab.back.json.response.CityResponseJson;
import edu.lab.back.service.crud.CityCrudService;
import edu.lab.back.util.ValidationMessages;
import edu.lab.back.util.exception.InvalidPayloadException;
import edu.lab.back.util.exception.ResourceNotFound;
import lombok.NonNull;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Stateless
@Transactional(rollbackOn = Exception.class)
public class CityCrudServiceImpl extends BaseService implements CityCrudService {

    private final CityDao cityDao;

    @Inject
    public CityCrudServiceImpl(@NonNull final CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public CityResponseJson getById(@NonNull final String idString) throws InvalidPayloadException, ResourceNotFound {
        final Long id = this.getId(idString);
        final CityEntity city = this.cityDao.getById(id);
        if (city == null) {
            throw new ResourceNotFound(ValidationMessages.RESOURCE_NOT_FOUND);
        }

        final CityResponseJson cityResponseJson = CityResponseJson.convert(city);

        return cityResponseJson;
    }

    @Override
    public List<CityResponseJson> getAll() {
        final List<CityEntity> allCities = this.cityDao.getAll();
        final List<CityResponseJson> allCitiesJson = allCities
            .stream()
            .filter(Objects::nonNull)
            .map(CityResponseJson::convert)
            .collect(Collectors.toList());
        return allCitiesJson;
    }

    @Override
    public CityResponseJson deleteById(@NonNull final String idString) throws InvalidPayloadException, ResourceNotFound {
        final Long id = this.getId(idString);
        final CityEntity deletedEntity = this.cityDao.deleteById(id);
        if (deletedEntity == null) {
            throw new ResourceNotFound(ValidationMessages.RESOURCE_NOT_FOUND);
        }

        final CityResponseJson deletedJson = CityResponseJson.convert(deletedEntity);

        return deletedJson;
    }

    @Override
    public CityResponseJson save(@NonNull final CityRequestJson cityJson) {
        final CityEntity cityEntity = CityEntity.convert(cityJson);

        final CityEntity saved = this.cityDao.save(cityEntity);
        final CityResponseJson result = CityResponseJson.convert(saved);

        return result;
    }

    @Override
    public CityResponseJson update(@NonNull final CityRequestJson cityJson) throws ResourceNotFound {
        final Long cityId = cityJson.getId();
        final CityEntity entity = this.cityDao.getById(cityId);
        if (entity == null) {
            throw new ResourceNotFound(ValidationMessages.RESOURCE_NOT_FOUND);
        }

        entity.setName(cityJson.getName());

        final CityEntity added = this.cityDao.update(entity);
        final CityResponseJson result = CityResponseJson.convert(added);

        return result;
    }

}
