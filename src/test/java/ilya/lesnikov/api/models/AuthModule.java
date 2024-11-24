package ilya.lesnikov.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthModule extends BaseModel {
    @Builder.Default
    private String name = "HTTP-Basic";
}
