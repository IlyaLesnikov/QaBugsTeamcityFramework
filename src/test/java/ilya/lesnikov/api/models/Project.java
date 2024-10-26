package ilya.lesnikov.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ilya.lesnikov.api.annotations.Parameterizable;
import ilya.lesnikov.api.annotations.Random;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project extends BaseModel {
    @Random
    @Parameterizable
    private String id;
    @Random
    private String name;
    private String locator;
}
