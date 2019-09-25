package edu.lab.back.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.lab.back.db.entity.ProfileEntity;
import edu.lab.back.db.entity.ProfileTypeEntity;
import edu.lab.back.util.ProfileTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class ProfileJson implements JsonPojo {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "age")
    private Integer age;

    @JsonProperty(value = "profile_type")
    private ProfileTypeEnum profileType;

    @JsonProperty(value = "class_level")
    private String classLevel;

    public static ProfileJson convert(@NonNull final ProfileEntity profileEntity) {
        final ProfileJson result = new ProfileJson();
        result.setAge(profileEntity.getAge());
        result.setClassLevel(profileEntity.getClassLevel());
        result.setId(profileEntity.getId());
        result.setName(profileEntity.getName());

        final ProfileTypeEntity profileType = profileEntity.getProfileType();
        if (profileType != null) {
            result.setProfileType(ProfileTypeEnum.getEnumByName(profileType.getName()));
        }

        return result;
    }

}
