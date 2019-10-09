package edu.lab.back.json.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.lab.back.db.entity.SchoolEntity;
import edu.lab.back.json.JsonPojo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class SchoolResponseJson implements JsonPojo {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "profiles")
    private List<ProfileJson> profiles;

    public static SchoolResponseJson convert(@NonNull final SchoolEntity schoolEntity) {
        SchoolResponseJson result = new SchoolResponseJson();
        result.setId(schoolEntity.getId());
        result.setName(schoolEntity.getName());

        if (schoolEntity.getProfiles() != null) {
            final List<ProfileJson> profilesJson = schoolEntity.getProfiles()
                .stream()
                .map(ProfileJson::convert)
                .collect(Collectors.toList());

            result.setProfiles(profilesJson);
        }

        return result;
    }

}
