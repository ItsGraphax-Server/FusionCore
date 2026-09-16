package de.itsgraphax.fusion.engine;

import de.itsgraphax.fusion.engine.text.positionedText.ComponentWidth;
import de.itsgraphax.fusion.engine.text.positionedText.Offset;
import de.itsgraphax.grphxLib.shorthands.OnEnable;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.object.ObjectContents;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public final class FusionEngine extends JavaPlugin {
    public static FusionEngine fusionEngine;

    public static String debugString = "Hello World!";

    public FusionEngine() {
        super();

        fusionEngine = this;
    }

    @Override
    public void onEnable() {
        OnEnable.registerEvents(Set.of(new TestListener()), this);

        getServer().getScheduler().runTaskTimer(this, () -> {
            var playerComponent = Component.empty();
            var onlinePlayers = getServer().getOnlinePlayers();

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
        }, 5, 5);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
