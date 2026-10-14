package de.itsgraphax.fusion.engine.gui.session;

import de.itsgraphax.fusion.engine.gui.Gui;
import de.itsgraphax.fusion.engine.gui.item.EmptyGuiItem;
import de.itsgraphax.fusion.engine.gui.item.GuiItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds the items in a {@link GuiSession} session.
 * This class only holds the items in a session. It does not trigger automatic rerenders, nor does it have a way of triggering one manually without the parent {@link GuiSession}
 */
public final class SessionItems {
    private final Gui gui;
    private final int slotAmount;

    private final List<@NotNull GuiItem> items = new ArrayList<>();

    /**
     * Initializes a SessionItems instance with the amount of slots in the gui
     *
     * @param gui The parent gui containing the settings values
     */
    public SessionItems(Gui gui) {
        this.gui = gui;
        slotAmount = gui.getSize() + (gui.usesPlayerInventory() ? 9 * 4 : 0);

        for (int i = 0; i < slotAmount; i++) {
            items.add(new EmptyGuiItem());
        }
    }


    private void verifySlotId(int slotId, int maxExclusive) {
        if (slotId < 0) throw new IllegalArgumentException("slotId cannot be negative");
        if (slotId >= maxExclusive)
            throw new IllegalArgumentException(String.format("slotId can't go above %s", maxExclusive));
    }

    public void setItem(int rawSlotId, GuiItem item) {
        verifySlotId(rawSlotId, slotAmount);
        items.set(rawSlotId, item);
    }

    public void setTopItem(int slotId, GuiItem item) {
        verifySlotId(slotId, gui.getSize());
        items.set(slotId, item);
    }

    /**
     * Sets the {@link GuiItem} in the player inventory. Overflowing to 27-35 fill the hotbar instead.
     *
     * @param slotId The slot id. 0 being the top left, 26 the bottom right and 27-35 the hotbar from left to right.
     * @param item   The item to put in that slot.
     */
    public void setInventoryItem(@Range(from = 0, to = 35) int slotId, GuiItem item) {
        if (!gui.usesPlayerInventory()) throw new RuntimeException("The gui dosen't use the player inventory");
        verifySlotId(slotId, 9 * 4);
        items.set(gui.getSize() + slotId, item);
    }

    /**
     * Sets the {@link GuiItem} in the player hotbar.
     *
     * @param slotId The slot id. 0 being the left, 8 being the right.
     * @param item   The item to put in that slot.
     */
    public void setHotbarItem(@Range(from = 0, to = 8) int slotId, GuiItem item) {
        if (!gui.usesPlayerInventory()) throw new RuntimeException("The gui dosen't use the player inventory");
        verifySlotId(slotId, 9);
        items.set(gui.getSize() + 9 * 3 + slotId, item);
    }


    public int getSize() {
        return slotAmount;
    }

    public GuiItem get(int slot) {
        return items.get(slot);
    }
}
