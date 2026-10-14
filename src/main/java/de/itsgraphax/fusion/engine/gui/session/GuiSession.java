package de.itsgraphax.fusion.engine.gui.session;

import de.itsgraphax.fusion.engine.gui.Gui;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.Nullable;

public final class GuiSession {
    private final InventoryView view;
    private final Gui gui;

    private final SessionData data = new SessionData();
    private final SessionItems items;
    private final @Nullable InventorySnapshot snapshot;

    public GuiSession(InventoryView view, Gui gui, @Nullable InventorySnapshot snapshot) {
        this.view = view;
        this.gui = gui;
        this.items = new SessionItems(gui);
        this.snapshot = snapshot;
    }

    public void init() {
        gui.onFirstRender(items);
        fullRender();
    }


    public InventoryView getView() {
        return view;
    }

    public Gui getGui() {
        return gui;
    }

    public SessionData getData() {
        return data;
    }

    public SessionItems getItems() {
        return items;
    }

    public Player getPlayer() {
        return (Player) view.getPlayer();
    }

    public @Nullable InventorySnapshot getSnapshot() {
        return snapshot;
    }

    public void fullRender() {
        for (int slot = 0; slot < items.getSize(); slot++) {
            render(slot);
        }
    }

    public void render(int slot) {
        view.setItem(slot, items.get(slot).getItem());
    }
}
