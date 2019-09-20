package edu.lab.back.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "city")
//@Data
@Getter
@Setter
@NoArgsConstructor
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_generator")
    @SequenceGenerator(name = "city_generator", sequenceName = "city_id_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city")
    private List<SchoolEntity> schools;


    @Override
    public String toString() {
        final String schoolsString = this.schools
            .stream()
            .map(s -> s.getId().toString())
            .reduce((r, s) -> r + ", " + s)
            .orElse("");

        return "CityEntity{" +
            "id=" + this.id +
            ", name='" + this.name + '\'' +
            ", schools_ids=" + schoolsString +
            '}';
    }
}
