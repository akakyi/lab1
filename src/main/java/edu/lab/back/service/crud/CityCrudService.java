package edu.lab.back.service.crud;

import edu.lab.back.db.dao.CityDao;
import edu.lab.back.db.dao.SchoolDao;
import edu.lab.back.db.entity.CityEntity;
import edu.lab.back.db.entity.SchoolEntity;
import edu.lab.back.json.CityJson;
import edu.lab.back.json.SchoolJson;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@NoArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class CityCrudService {

    private CityDao cityDao;

    private SchoolDao schoolDao;

    @Inject
    public CityCrudService(@NonNull final CityDao cityDao, @NonNull final SchoolDao schoolDao) {
        this.cityDao = cityDao;
        this.schoolDao = schoolDao;
    }

    public CityJson getById(@NonNull final String idString) {
        final Long id = this.getId(idString);
        final CityEntity city = this.cityDao.getById(id, CityEntity.class);
        city.getSchools();
        final CityJson cityJson = CityJson.convert(city);

        return cityJson;
    }

    public List<CityJson> getAll() {
        final List<CityEntity> allCities = this.cityDao.getAll(CityEntity.class);
        final List<CityJson> allCitiesJson = allCities
            .stream()
            .map(CityJson::convert)
            .collect(Collectors.toList());
        return allCitiesJson;
    }

    public CityJson deleteById(@NonNull final String idString) {
        final Long id = this.getId(idString);
        final CityEntity deletedEntity = this.cityDao.deleteById(id, CityEntity.class);
        final CityJson deletedJson = CityJson.convert(deletedEntity);

        return deletedJson;
    }

    public CityJson save(@NonNull final CityJson cityJson) {
        final CityEntity cityEntity = CityEntity.convert(cityJson);
        final List<SchoolEntity> schools = this.getSchools(cityJson);
        cityEntity.setSchools(schools);

        final CityEntity saved = this.cityDao.save(cityEntity);
        final CityJson result = CityJson.convert(saved);

        return result;
    }

    public CityJson update(@NonNull final CityJson cityJson) {
        final Long cityId = cityJson.getId();
        final CityEntity entity = this.cityDao.getById(cityId, CityEntity.class);

        entity.setName(cityJson.getName());

        final List<SchoolEntity> schools = this.getSchools(cityJson);
        entity.setSchools(schools);

        final CityEntity added = this.cityDao.update(entity);
        final CityJson result = CityJson.convert(added);

        return result;
    }

    private Long getId(@NonNull final String idString) {
        final long id = Long.parseLong(idString);
        return id;
    }

    private List<SchoolEntity> getSchools(@NonNull final CityJson cityJson) {
        if (cityJson.getSchools() == null) {
            return new ArrayList<>();
        }
        final List<Long> schoolsIds = cityJson.getSchools()
            .stream()
            .map(SchoolJson::getId)
            .collect(Collectors.toList());
        final List<SchoolEntity> schools = this.schoolDao.getByIds(schoolsIds);

        return schools;
    }

}
