package edu.lab.back.db.dao.implementations.postgres;

import edu.lab.back.db.dao.SchoolDao;
import edu.lab.back.db.entity.SchoolEntity;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@NoArgsConstructor
public class SchoolDaoImpl extends BaseCrudDaoImpl<SchoolEntity, Long> implements SchoolDao {

    private final static String SCHOOLS_BY_CITY_REQUEST = "select s from SchoolEntity s where s.city.id in :cityId";

    private final static String SCHOOLS_BY_IDS = "select s from SchoolEntity s where s.id = :ids";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SchoolEntity> getByIds(@NonNull final List<Long> ids) {
        final TypedQuery<SchoolEntity> query
            = this.entityManager.createQuery(SCHOOLS_BY_IDS, SchoolEntity.class);
        query.setParameter("ids", ids);

        final List<SchoolEntity> result = query.getResultList();
        return result;
    }

    @Override
    public List<SchoolEntity> getSchoolsByCityId(@NonNull final Long cityId) {
        final TypedQuery<SchoolEntity> query
            = this.entityManager.createQuery(SCHOOLS_BY_CITY_REQUEST, SchoolEntity.class);
        query.setParameter("cityId", cityId);

        final List<SchoolEntity> result = query.getResultList();
        return result;
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }
}
