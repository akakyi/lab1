package edu.lab.back.service.crud;

import edu.lab.back.db.dao.CityDao;
import edu.lab.back.db.entity.CityEntity;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Stateless
@NoArgsConstructor
public class CityCrudService {

    private CityDao cityDao;

    @Inject
    public CityCrudService(@NonNull final CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Transactional
    public CityEntity getById(final Long id) {
        final CityEntity city = this.cityDao.getById(id);
        return city;
    }

}
