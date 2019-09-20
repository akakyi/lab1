package edu.lab.back.db.dao;

import edu.lab.back.db.entity.SchoolEntity;

public interface SchoolDao {

    SchoolEntity getById(Long id);

    SchoolEntity deleteById(Long id);

    SchoolEntity update(SchoolEntity city);

    SchoolEntity add(SchoolEntity city);


}
