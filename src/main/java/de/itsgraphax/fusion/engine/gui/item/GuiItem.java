package de.itsgraphax.fusion.engine.gui.item;

import de.itsgraphax.fusion.engine.gui.ClickContext;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface GuiItem {
    @NotNull ItemStack getItem();
    default void onClick(ClickContext ctx) {}
}
