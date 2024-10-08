package ilya.lesnikov.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import ilya.lesnikov.api.enums.UserRoles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role extends BaseModel{
    @Builder.Default
    private String roleId = String.valueOf(UserRoles.SYSTEM_ADMIN);
    @Builder.Default
    private String scope = "g";
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String href = null;
}
