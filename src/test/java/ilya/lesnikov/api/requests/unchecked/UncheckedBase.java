package ilya.lesnikov.api.requests.unchecked;

import ilya.lesnikov.api.enums.Endpoint;
import ilya.lesnikov.api.models.BaseModel;
import ilya.lesnikov.api.requests.CrudInterface;
import ilya.lesnikov.api.requests.Request;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class UncheckedBase extends Request implements CrudInterface {

    public UncheckedBase(RequestSpecification spec, Endpoint endpoint) {
        super(spec, endpoint);
    }

    @Override
    public Response create(BaseModel model) {
        return given()
                .spec(spec)
                .body(model)
                .post(endpoint.getUrl());
    }

    @Override
    public Response read(String id) {
        return given()
                .spec(spec)
                .get(String.format("%s/id:%s", endpoint.getUrl(), id));
    }

    @Override
    public Response update(String id, BaseModel model) {
        return given()
                .spec(spec)
                .body(model)
                .put(String.format("%s/id:%s", endpoint.getUrl(), id));
    }

    @Override
    public Response delete(String id) {
        return given()
                .spec(spec)
                .delete(String.format("%s/id:%s", endpoint.getUrl(), id));
    }
}
