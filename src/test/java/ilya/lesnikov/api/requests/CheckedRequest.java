package ilya.lesnikov.api.requests;

import ilya.lesnikov.api.enums.Endpoint;
import ilya.lesnikov.api.models.BaseModel;
import ilya.lesnikov.api.requests.checked.CheckedBase;
import io.restassured.specification.RequestSpecification;

import java.util.EnumMap;

public class CheckedRequest {
    private final EnumMap<Endpoint, CheckedBase> requests = new EnumMap<>(Endpoint.class);

    public CheckedRequest(RequestSpecification spec) {
        for (var endpoint: Endpoint.values()) {
            requests.put(endpoint, new CheckedBase(spec, endpoint));
        }
    }

    public <T extends BaseModel> CheckedBase<T> getRequest(Endpoint endpoint) {
        return (CheckedBase<T>) requests.get(endpoint);
    }
}
