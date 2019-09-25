package edu.lab.back.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.lab.back.db.entity.CityEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CityJson implements JsonPojo {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "schools")
    private List<SchoolJson> schools;

    public static CityJson convert(@NonNull final CityEntity cityEntity) {
        final CityJson cityJson = new CityJson();
        cityJson.setId(cityEntity.getId());
        cityJson.setName(cityEntity.getName());

        final List<SchoolJson> schools = cityEntity.getSchools()
            .stream()
            .map(SchoolJson::convert)
            .collect(Collectors.toList());

        cityJson.setSchools(schools);

        return cityJson;
    }

}
