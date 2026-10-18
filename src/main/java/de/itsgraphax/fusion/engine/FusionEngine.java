package de.itsgraphax.fusion.engine;

import de.itsgraphax.fusion.engine.misc.ContainerBackground;
import de.itsgraphax.grphxLib.shorthands.OnEnable;
import de.itsgraphax.grphxLib.utils.ResourcepackSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class FusionEngine extends JavaPlugin {
    public static FusionEngine fusionEngine;

    public FusionEngine() {
        super();

        fusionEngine = this;
    }

    @Override
    public void onEnable() {
        OnEnable.registerEvents(this,
                new ResourcepackSender("fusion", "1.0.1"),
                new ContainerBackground()
        );
    }

}
