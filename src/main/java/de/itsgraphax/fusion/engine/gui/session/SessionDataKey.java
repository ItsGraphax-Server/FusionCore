package de.itsgraphax.fusion.engine.gui.session;

public record SessionDataKey<T>(String name, Class<T> type) {
    public static <T> SessionDataKey<T> of(String name, Class<T> type) {
        return new SessionDataKey<>(name, type);
    }
}
