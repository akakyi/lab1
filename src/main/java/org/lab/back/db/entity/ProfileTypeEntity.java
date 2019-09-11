package org.lab.back.db.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "profile_type")
@Data
@NoArgsConstructor
public class ProfileTypeEntity {

    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

}
