package de.itsgraphax.fusion.engine;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TestListener implements Listener {
    @EventHandler
    void onChat(AsyncChatEvent e) {
        if (!(e.message() instanceof TextComponent c)) return;
        FusionEngine.debugString = c.content();
    }
}
