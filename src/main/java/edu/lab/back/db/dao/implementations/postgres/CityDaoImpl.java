package edu.lab.back.db.dao.implementations.postgres;

import edu.lab.back.db.dao.CityDao;
import edu.lab.back.db.entity.CityEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CityDaoImpl extends BaseCrudDaoImpl<CityEntity, Long> implements CityDao {

    @PersistenceContext
    private EntityManager entityManager;

    public CityDaoImpl() {
        super(CityEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }
}
