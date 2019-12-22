package edu.lab.back.db.dao.implementations.postgres;

import edu.lab.back.db.dao.BaseCrudDao;
import lombok.NonNull;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class BaseCrudDaoImpl<EntityType, IdType> implements BaseCrudDao<EntityType, IdType> {
    
    private Class<EntityType> entityClass;
    
    protected BaseCrudDaoImpl(@NonNull final Class<EntityType> entityClass) {
        this.entityClass = entityClass;
    }
    
    @Override
    public EntityType getById(@NonNull final IdType id) {
        final EntityType city = this.getEntityManager().find(this.entityClass, id);
        return city;
    }

    @Override
    public List<EntityType> getAll() {
        final CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<EntityType> query = criteriaBuilder.createQuery(this.entityClass);
        final Root<EntityType> from = query.from(this.entityClass);
        final CriteriaQuery<EntityType> allSelection = query.select(from);

        final TypedQuery<EntityType> resultQuery = this.getEntityManager().createQuery(allSelection);
        final List<EntityType> result = resultQuery.getResultList();

        return result;
    }

    @Override
    public EntityType deleteById(final IdType id) {
        final EntityType city = this.getById(id);
        if (city == null) {
            return null;
        }

        this.getEntityManager().remove(city);
        return city;
    }

    @Override
    public EntityType update(final EntityType city) {
        this.getEntityManager().merge(city);
        return city;
    }

    @Override
    public EntityType save(final EntityType city) {
        final EntityManager entityManager = this.getEntityManager();
        entityManager.persist(city);
        return city;
    }
    
    protected abstract EntityManager getEntityManager();
}
