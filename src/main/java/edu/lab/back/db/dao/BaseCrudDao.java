package edu.lab.back.db.dao;

import java.util.List;

//TODO мб заменить на абстрактный класс?
public interface BaseCrudDao <EntityType, IdType> {

    EntityType getById(IdType id);

    List<EntityType> getAll();

    EntityType deleteById(IdType id);

    EntityType update(EntityType entity);

    EntityType save(EntityType entity);

}
