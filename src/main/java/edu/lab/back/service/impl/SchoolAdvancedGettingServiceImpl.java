package edu.lab.back.service.impl;

import edu.lab.back.db.dao.SchoolDao;
import edu.lab.back.db.entity.SchoolEntity;
import edu.lab.back.json.response.SchoolResponseJson;
import edu.lab.back.service.SchoolAdvancedGettingService;
import edu.lab.back.service.crud.implementations.BaseService;
import edu.lab.back.util.exception.InvalidPayloadException;
import lombok.NonNull;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class SchoolAdvancedGettingServiceImpl extends BaseService implements SchoolAdvancedGettingService {

    private final SchoolDao schoolDao;

    @Inject
    public SchoolAdvancedGettingServiceImpl(@NonNull final SchoolDao schoolDao) {
        this.schoolDao = schoolDao;
    }

    @Override
    public List<SchoolResponseJson> getSchoolsByCityId(@NonNull final String cityId) throws InvalidPayloadException {
        final Long id = this.getId(cityId);
        final List<SchoolEntity> schools = this.schoolDao.getSchoolsByCityId(id);
        final List<SchoolResponseJson> result = schools.stream()
            .map(SchoolResponseJson::convert)
            .collect(Collectors.toList());

        return result;
    }
}
