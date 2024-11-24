package ilya.lesnikov.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServerAuthSettings extends BaseModel {
    private Boolean perProjectPermission;
    private AuthModules modules;
}
