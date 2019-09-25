package edu.lab.back.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.lab.back.db.entity.SchoolEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class SchoolJson implements JsonPojo {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "profiles")
    private List<ProfileJson> profiles;

    public static SchoolJson convert(@NonNull final SchoolEntity schoolEntity) {
        SchoolJson result = new SchoolJson();
        result.setId(schoolEntity.getId());
        result.setName(schoolEntity.getName());

        final List<ProfileJson> profilesJson = schoolEntity.getProfiles()
            .stream()
            .map(ProfileJson::convert)
            .collect(Collectors.toList());

        result.setProfiles(profilesJson);

        return result;
    }

}
