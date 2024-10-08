package ilya.lesnikov.api.spec;

import ilya.lesnikov.api.config.Configuration;
import ilya.lesnikov.api.models.User;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Specifications {

    private Specifications() {}

    private static RequestSpecBuilder requestSpecBuilder() {
        return new RequestSpecBuilder()
                .setAccept(ContentType.JSON);
    }

    public static RequestSpecification unAuthSpec() {
        return requestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification superUserAuthSpec() {
        BasicAuthScheme basicAuthScheme = new BasicAuthScheme();
        basicAuthScheme.setUserName("");
        basicAuthScheme.setPassword(Configuration.getProperties("superUserToken"));
        return requestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAuth(basicAuthScheme)
                .build();
    }

    public static RequestSpecification authSpec(User user) {
        BasicAuthScheme basicAuthScheme = new BasicAuthScheme();
        basicAuthScheme.setUserName(user.getUsername());
        basicAuthScheme.setPassword(user.getPassword());
        return requestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAuth(basicAuthScheme)
                .build();
    }

}
