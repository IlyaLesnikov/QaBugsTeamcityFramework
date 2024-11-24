package ilya.lesnikov.api.tests;

import ilya.lesnikov.BaseTest;
import ilya.lesnikov.api.models.AuthModules;
import ilya.lesnikov.api.models.ServerAuthSettings;
import ilya.lesnikov.api.requests.ServerAuthRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static ilya.lesnikov.api.generatos.TestDataGenerator.generate;
import static ilya.lesnikov.api.spec.Specifications.superUserAuthSpec;

public class BaseApiTest extends BaseTest {
    private final ServerAuthRequest serverAuthRequest = new ServerAuthRequest(superUserAuthSpec());
    private AuthModules authModules;
    private boolean perProjectPermissions;

    @BeforeEach
    public void setUpServerAuthSettings() {
        perProjectPermissions = serverAuthRequest.read().getPerProjectPermission();

        authModules = generate(AuthModules.class);

        serverAuthRequest.update(ServerAuthSettings.builder()
                .perProjectPermission(true)
                .modules(authModules)
                .build());
    }

    @AfterEach
    public void cleanUpServerAuthSettings() {
        serverAuthRequest.update(ServerAuthSettings.builder()
                .perProjectPermission(perProjectPermissions)
                .modules(authModules)
                .build());
    }
}
