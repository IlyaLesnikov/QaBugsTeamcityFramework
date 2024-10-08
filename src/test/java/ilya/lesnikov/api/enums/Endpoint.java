package ilya.lesnikov.api.enums;

import ilya.lesnikov.api.models.BaseModel;
import ilya.lesnikov.api.models.BuildType;
import ilya.lesnikov.api.models.Project;
import ilya.lesnikov.api.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Endpoint {
    BUILD_TYPES("/app/rest/buildTypes", BuildType.class),
    PROJECTS("/app/rest/projects", Project.class),
    USERS("/app/rest/users", User.class);

    private final String url;
    private final Class<? extends BaseModel> modelClass;
}
