package de.itsgraphax.fusion.engine.gui;

import de.itsgraphax.fusion.engine.gui.session.GuiSession;
import org.bukkit.event.inventory.InventoryClickEvent;

public record ClickContext(
        GuiSession session,
        InventoryClickEvent event
) { }