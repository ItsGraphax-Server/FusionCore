package de.itsgraphax.fusion.engine.gui;

import de.itsgraphax.fusion.engine.gui.session.GuiSession;
import de.itsgraphax.fusion.engine.gui.session.GuiSessionManager;
import org.bukkit.GameRules;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.Nullable;

public final class GuiListener implements Listener {
    public static final GuiSessionManager sessionManager = GuiSessionManager.getManager();


    private @Nullable GuiSession getSessionFromEvent(InventoryEvent event) {
        if (!(event.getView().getPlayer() instanceof Player player)) return null;
        var session = sessionManager.getSession(player);
        if (session == null ||
                session.getView() != event.getView()) // shouldnt happen but better safe than sorry
            return null;
        return session;
    }


    @EventHandler
    void onInventoryClose(InventoryCloseEvent event) {
        sessionManager.closed((Player) event.getPlayer());
    }

    @EventHandler(priority = EventPriority.LOW)
    void onResurrect(EntityResurrectEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.getWorld().getGameRuleValue(GameRules.KEEP_INVENTORY)) return;
        if (!event.isCancelled()) return;

        sessionManager.closed(player, true);
    }

    @EventHandler
    void onLeave(PlayerQuitEvent event) {
        GuiSessionManager.getManager().closed(event.getPlayer());
    }


    @EventHandler
    void onInventoryDrag(InventoryDragEvent event) {
        var session = getSessionFromEvent(event);
        if (session == null) return;

        if (session.getGui().usesPlayerInventory()) {
            event.setCancelled(true);
            return;
        }

        for (int rawSlotId : event.getRawSlots()) {
            if (rawSlotId < session.getGui().getSize()) {
                event.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler
    void onInventoryClick(InventoryClickEvent event) {
        var session = getSessionFromEvent(event);
        if (session == null) return;

        if (!session.getGui().usesPlayerInventory() &&
                event.getRawSlot() >= session.getGui().getSize()) return;

        event.setCancelled(true);
        session.getItems().get(event.getRawSlot()).onClick(new ClickContext(session, event));

    }


    @EventHandler
    void onItemDrop(PlayerDropItemEvent event) {
        var session = sessionManager.getSession(event.getPlayer());
        if (session == null) return;

        event.setCancelled(true);
    }

    @EventHandler
    void onItemPickup(PlayerAttemptPickupItemEvent event) {
        var session = sessionManager.getSession(event.getPlayer());
        if (session == null) return;

        if (session.getGui().usesPlayerInventory()) {
            event.setCancelled(true);
        }
    }
}
