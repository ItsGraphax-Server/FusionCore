package de.itsgraphax.fusion.engine.misc;

import org.bukkit.NamespacedKey;

public interface Namespaces {
    static NamespacedKey key(String namespace, String key) {
        return new NamespacedKey(namespace, key);
    }

    static NamespacedKey key(String key) {
        return key("fusion", key);
    }

    interface Fonts {
        NamespacedKey OFFSET = key("offset");
        NamespacedKey VANILLA_CONTAINERS = key("vanilla_containers");
        NamespacedKey DEFAULT = key("minecraft", "default");
    }
}
