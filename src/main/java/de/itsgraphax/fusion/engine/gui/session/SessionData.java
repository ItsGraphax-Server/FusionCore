package de.itsgraphax.fusion.engine.gui.session;

import java.util.HashMap;
import java.util.Map;

public final class SessionData {

    private final Map<SessionDataKey<?>, Object> values = new HashMap<>();

    public <T> void set(SessionDataKey<T> key, T value) {
        values.put(key, key.type().cast(value));
    }

    public <T> T get(SessionDataKey<T> key) {
        return key.type().cast(values.get(key));
    }

    public <T> T get(SessionDataKey<T> key, T def) {
        T value = get(key);
        return value != null ? value : def;
    }

    public void remove(SessionDataKey<?> key) {
        values.remove(key);
    }

    public boolean has(SessionDataKey<?> key) {
        return values.containsKey(key);
    }
}
