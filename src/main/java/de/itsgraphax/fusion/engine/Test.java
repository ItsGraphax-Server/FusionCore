package de.itsgraphax.fusion.engine;

import de.itsgraphax.fusion.engine.text.positionedText.ComponentWidth;
import de.itsgraphax.fusion.engine.text.positionedText.Offset;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.object.ObjectContents;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static de.itsgraphax.fusion.engine.FusionEngine.fusionEngine;

public class Test implements Listener {
    @EventHandler
    void onChat(AsyncChatEvent e) {
        if (!(e.message() instanceof TextComponent c)) return;
        FusionEngine.debugString = c.content();
    }

    public static void tick() {
        var playerComponent = Component.empty();
        var onlinePlayers = fusionEngine.getServer().getOnlinePlayers();

        for (Player p : onlinePlayers) {
            playerComponent = playerComponent
                    .append(Component.object().contents(ObjectContents.playerHead(p.getUniqueId())))
                    .append(Component.text(" "));
        }
        var width = ComponentWidth.calculateSingleComponentWidth(playerComponent);

        var c = Component.empty()
                .append(Offset.createOffset(-100 + width))
                .append(playerComponent)
                .append(Offset.createOffset(100));

        Audience.audience(onlinePlayers).sendActionBar(c);
    }
}
