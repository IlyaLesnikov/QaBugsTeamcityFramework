package ilya.lesnikov.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRoles {
    SYSTEM_ADMIN("SYSTEM_ADMIN");

    private final String role;
}
