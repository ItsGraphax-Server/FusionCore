package de.itsgraphax.fusion.engine.gui.item;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class EmptyGuiItem implements GuiItem {
    @Override
    public @NotNull ItemStack getItem() {
        return ItemStack.empty();
    }
}
