package edu.lab.back.db.dao.implementations.postgres;

import edu.lab.back.db.dao.CityDao;
import edu.lab.back.db.entity.CityEntity;
import lombok.NoArgsConstructor;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@NoArgsConstructor
public class CityDaoImpl implements CityDao {

    @PersistenceContext
    private EntityManager entityManager;

    public CityEntity getById(final Long id) {
        final CityEntity city = this.entityManager.find(CityEntity.class, id);
        return city;
    }

    public CityEntity deleteById(final Long id) {
        final CityEntity city = this.getById(id);
        this.entityManager.remove(city);
        return city;
    }

    public CityEntity update(final CityEntity city) {
        this.entityManager.persist(city);
        return city;
    }

    public CityEntity add(final CityEntity city) {
        this.entityManager.persist(city);
        return city;
    }
}
