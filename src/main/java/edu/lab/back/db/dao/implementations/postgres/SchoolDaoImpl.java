package edu.lab.back.db.dao.implementations.postgres;

import edu.lab.back.db.dao.SchoolDao;
import edu.lab.back.db.entity.SchoolEntity;
import lombok.NoArgsConstructor;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@NoArgsConstructor
public class SchoolDaoImpl implements SchoolDao {

    @PersistenceContext
    private EntityManager entityManager;

    public SchoolEntity getById(final Long id) {
        return entityManager.find(SchoolEntity.class, id);
    }

    public SchoolEntity deleteById(final Long id) {
        final SchoolEntity school = this.getById(id);
        this.entityManager.remove(school);
        return school;
    }

    public SchoolEntity update(final SchoolEntity schoolEntity) {
        this.entityManager.persist(schoolEntity);
        return schoolEntity;
    }

    public SchoolEntity add(final SchoolEntity schoolEntity) {
        this.entityManager.persist(schoolEntity);
        return schoolEntity;
    }
}
