package de.itsgraphax.fusion.engine.gui;

import de.itsgraphax.fusion.engine.gui.session.GuiSession;
import de.itsgraphax.fusion.engine.gui.session.SessionItems;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class Gui {
    private final int size;
    private final Component title;
    private final boolean usesPlayerInventory;

    protected Gui(int size, @NotNull Component title, boolean usesPlayerInventory) {
        if (size < 9 || size > 54 || size % 9 != 0)
            throw new IllegalArgumentException("Gui size must be a multiple of 9 between 9 and 54");

        this.size = size;
        this.title = Objects.requireNonNull(title, "title can't be null");
        this.usesPlayerInventory = usesPlayerInventory;
    }

    /**
     * Gets the amount of slots in the chest gui.
     *
     * @return The amount of slots in the chest without inventory slots.
     */
    public final int getSize() {
        return size;
    }

    /**
     * Gets the title of the chest gui.
     *
     * @return The title of the chest.
     */
    public final Component getTitle() {
        return title;
    }

    /**
     * Gets if the gui uses the player inventory as extra ui space.
     *
     * @return True if it uses the player inventory, false otherwise.
     */
    public final boolean usesPlayerInventory() {
        return usesPlayerInventory;
    }

    /**
     * Gets called when the gui gets first rendered on open.
     *
     * @param items A {@link SessionItems} with the items in the player gui.
     */
    public void onFirstRender(SessionItems items) {}

    /**
     * Gets called when the gui gets closed by a player.
     * This event has the same limitations as {@link org.bukkit.event.inventory.InventoryCloseEvent}. Opening/closing inventories is not supported and should be delayed by a tick.
     *
     * @param session The current {@link GuiSession} that is being closed
     */
    public void onClose(GuiSession session) {}
}