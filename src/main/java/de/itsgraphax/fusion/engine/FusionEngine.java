package de.itsgraphax.fusion.engine;

import de.itsgraphax.fusion.engine.text.positionedText.ComponentWidth;
import de.itsgraphax.fusion.engine.text.positionedText.Offset;
import de.itsgraphax.grphxLib.shorthands.OnEnable;
import de.itsgraphax.grphxLib.utils.ResourcepackSender;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.object.ObjectContents;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;
import java.util.UUID;

public final class FusionEngine extends JavaPlugin {
    public static FusionEngine fusionEngine;

    public static String debugString = "Hello World!";

    public FusionEngine() {
        super();

        fusionEngine = this;
    }

    @Override
    public void onEnable() {
        OnEnable.registerEvents(this,
                new ResourcepackSender("fusion", "1.0.0"));

        // OnEnable.registerEvents(Set.of(new Test()), this);
        // getServer().getScheduler().runTaskTimer(this, Test::tick, 5, 5);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
