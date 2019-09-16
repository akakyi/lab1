package edu.lab.back.db.dao;

import edu.lab.back.db.entity.CityEntity;

public interface CityDao {

    CityEntity getById(Long id);

    CityEntity deleteById(Long id);

    CityEntity update(CityEntity city);

    CityEntity add(CityEntity city);

}
