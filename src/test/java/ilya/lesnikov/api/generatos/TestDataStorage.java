package ilya.lesnikov.api.generatos;

import ilya.lesnikov.api.enums.Endpoint;
import ilya.lesnikov.api.models.BaseModel;
import ilya.lesnikov.api.requests.unchecked.UncheckedBase;
import ilya.lesnikov.api.spec.Specifications;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TestDataStorage {
    private static TestDataStorage testDataStorage;
    private final EnumMap<Endpoint, Set<String>> createdEntitiesMap;

    private TestDataStorage() {
        createdEntitiesMap = new EnumMap<>(Endpoint.class);
    }

    public static TestDataStorage getInstance() {
        if (testDataStorage == null) {
            synchronized (TestDataStorage.class) {
                if (testDataStorage == null) {
                    testDataStorage = new TestDataStorage();
                }
            }
        }
        return testDataStorage;
    }

    private void addCreatedEntite(Endpoint endpoint, String id) {
        if (id != null && !id.isEmpty()) {
            createdEntitiesMap.computeIfAbsent(endpoint, key -> new HashSet<>()).add(id);
        }
    }

    private String getEntityIdOrLocator(BaseModel model) {
        try {
            var idField = model.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            var idFieldValue = Objects.toString(idField.get(model), null);
            idField.setAccessible(false);
            return idFieldValue;
        } catch (NoSuchFieldException | IllegalAccessException e1) {
            try {
                var locatorField = model.getClass().getDeclaredField("locator");
                locatorField.setAccessible(true);
                var locatorFieldValue = Objects.toString(locatorField.get(model), null);
                return locatorFieldValue;
            } catch (NoSuchFieldException | IllegalAccessException e2) {
                throw new IllegalStateException("Cannot get ID or Locator of entity", e2);
            }
        }
    }

    public void addCreatedEntity(Endpoint endpoint, BaseModel model) {
        addCreatedEntite(endpoint, getEntityIdOrLocator(model));
    }

    public void deleteCreatedEntities() {
        createdEntitiesMap.forEach(
            (endpoint, ids) -> ids.forEach(
                id -> new UncheckedBase(Specifications.superUserAuthSpec(), endpoint).delete(id)
            )
        );

        createdEntitiesMap.clear();
    }
}
