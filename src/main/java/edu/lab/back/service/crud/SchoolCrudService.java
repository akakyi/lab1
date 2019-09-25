package edu.lab.back.service.crud;

import edu.lab.back.db.dao.SchoolDao;
import edu.lab.back.db.entity.SchoolEntity;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Stateless
@NoArgsConstructor
public class SchoolCrudService {

    private SchoolDao schoolDao;

    @Inject
    public SchoolCrudService(@NonNull final SchoolDao schoolDao) {
        this.schoolDao = schoolDao;
    }

    @Transactional
    public String getById(final Long id) {
        final SchoolEntity school = this.schoolDao.getById(id, SchoolEntity.class);
        return school.toString();
    }

}
