package ilya.lesnikov.api.requests;

import ilya.lesnikov.api.models.ServerAuthSettings;
import io.restassured.specification.RequestSpecification;

import javax.net.ssl.HttpsURLConnection;

import static io.restassured.RestAssured.given;

public class ServerAuthRequest {
    private static final String SERVER_AUTH_ENDPOINT = "app/rest/server/authSettings";
    private RequestSpecification spec;

    public ServerAuthRequest(RequestSpecification spec) {
        this.spec = spec;
    }

    public ServerAuthSettings read() {
        return given()
                .spec(spec)
                .get(SERVER_AUTH_ENDPOINT)
                .then()
                .statusCode(HttpsURLConnection.HTTP_OK)
                .extract().as(ServerAuthSettings.class);
    }

    public ServerAuthSettings update(ServerAuthSettings authSettings) {
        return given()
                .spec(spec)
                .body(authSettings)
                .put(SERVER_AUTH_ENDPOINT)
                .then()
                .statusCode(HttpsURLConnection.HTTP_OK)
                .extract().as(ServerAuthSettings.class);
    }
}
