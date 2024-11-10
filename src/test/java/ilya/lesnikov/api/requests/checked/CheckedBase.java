package ilya.lesnikov.api.requests.checked;

import ilya.lesnikov.api.enums.Endpoint;
import ilya.lesnikov.api.generatos.TestDataStorage;
import ilya.lesnikov.api.models.BaseModel;
import ilya.lesnikov.api.requests.CrudInterface;
import ilya.lesnikov.api.requests.Request;
import ilya.lesnikov.api.requests.unchecked.UncheckedBase;
import io.restassured.specification.RequestSpecification;

import java.net.HttpURLConnection;

@SuppressWarnings("unchecked")
public class CheckedBase<T extends BaseModel> extends Request implements CrudInterface {
    private final UncheckedBase uncheckedBase;

    public CheckedBase(RequestSpecification spec, Endpoint endpoint) {
        super(spec, endpoint);
        this.uncheckedBase = new UncheckedBase(spec, endpoint);
    }

    @Override
    public T create(BaseModel model) {
        var createdModel = (T) uncheckedBase.create(model)
                .then()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract().as(endpoint.getModelClass());

        TestDataStorage.getInstance().addCreatedEntity(endpoint, createdModel);

        return createdModel;
    }

    @Override
    public T read(String id) {
        return (T) uncheckedBase.read(id)
                .then()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract().as(endpoint.getModelClass());
    }

    @Override
    public T update(String id, BaseModel model) {
        return (T) uncheckedBase.update(id, model)
                .then()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract().as(endpoint.getModelClass());
    }

    @Override
    public String delete(String id) {
        return uncheckedBase.delete(id)
                .then()
                .statusCode(HttpURLConnection.HTTP_NO_CONTENT)
                .extract().asString();
    }
}
