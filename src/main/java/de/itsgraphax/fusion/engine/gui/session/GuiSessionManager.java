package de.itsgraphax.fusion.engine.gui.session;

import de.itsgraphax.fusion.engine.gui.Gui;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class GuiSessionManager {
    private static final GuiSessionManager instance = new GuiSessionManager();

    private final Map<UUID, GuiSession> sessions = new HashMap<>();


    private GuiSessionManager() {
    }

    public static GuiSessionManager getManager() {
        return instance;
    }

    public void open(Player player, Gui gui) {
        Inventory inventory = Bukkit.createInventory(player, gui.getSize(), gui.getTitle());
        InventoryView view = player.openInventory(inventory);

        var snapshot = gui.usesPlayerInventory() ? InventorySnapshot.takeSnapshotAndClear(player) : null;

        GuiSession session = new GuiSession(view, gui, snapshot);
        sessions.put(player.getUniqueId(), session);
        session.init();
    }

    public void closed(Player player) {
        closed(player, false);
    }

    public void closed(Player player, boolean closeIfOpen) {
        var session = sessions.remove(player.getUniqueId());

        if (session == null) return;

        if (
                closeIfOpen &&
                player.getOpenInventory() == session.getView()) player.closeInventory();

        InventorySnapshot snapshot = session.getSnapshot();
        if (snapshot != null) snapshot.giveBackToPlayer(player);

        session.getGui().onClose(session);
    }

    public @Nullable GuiSession getSession(Player player) {
        return sessions.get(player.getUniqueId());
    }
}
