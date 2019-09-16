package edu.lab.back.db.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "school")
@Data
@NoArgsConstructor
public class SchoolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "school_generator")
    @SequenceGenerator(name = "school_generator", sequenceName = "school_id_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private CityEntity city;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ProfileEntity> profiles;

}
