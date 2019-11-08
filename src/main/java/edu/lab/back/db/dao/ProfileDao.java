package edu.lab.back.db.dao;

import edu.lab.back.db.entity.ProfileEntity;

import java.util.List;

public interface ProfileDao extends BaseCrudDao<ProfileEntity, Long> {

    List<ProfileEntity> getProfilesBySchoolId(Long schoolId);

}
