package edu.lab.back.db.dao.implementations.postgres;

import edu.lab.back.db.dao.ProfileDao;
import edu.lab.back.db.entity.ProfileEntity;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@NoArgsConstructor
public class ProfileDaoImpl extends BaseCrudDaoImpl<ProfileEntity, Long> implements ProfileDao {

    private final static String PROFILES_BY_SCHOOL_REQUEST = "select p from ProfileEntity p where p.school.id = :schoolId";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    public List<ProfileEntity> getProfilesBySchoolId(@NonNull final Long schoolId) {
        final TypedQuery<ProfileEntity> query
            = this.entityManager.createQuery(PROFILES_BY_SCHOOL_REQUEST, ProfileEntity.class);
        query.setParameter("schoolId", schoolId);

        final List<ProfileEntity> result = query.getResultList();
        return result;
    }

}
