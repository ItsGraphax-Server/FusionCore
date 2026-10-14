package de.itsgraphax.fusion.engine.gui.session;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public record InventorySnapshot(ItemStack[] items) {
    public static InventorySnapshot takeSnapshotAndClear(Player player) {
        var inv = player.getInventory();
        var items = inv.getStorageContents();
        inv.setStorageContents(new ItemStack[]{});

        return new InventorySnapshot(items);
    }

    public void giveBackToPlayer(Player player) {
        player.getInventory().setStorageContents(items);
    }
}
