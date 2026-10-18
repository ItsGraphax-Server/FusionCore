package de.itsgraphax.fusion.engine.misc;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.block.Barrel;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.block.EnderChest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class ContainerBackground implements Listener {

    @EventHandler
    void onInventoryOpen(InventoryOpenEvent event) {
        Inventory inventory = event.getInventory();
        InventoryHolder holder = inventory.getHolder();

        if (holder instanceof Chest || holder instanceof DoubleChest || holder instanceof Barrel || holder instanceof EnderChest) {
            Component title = Component
                    .text(String
                            .format("\u0000%s\u0001", (char) inventory.getSize()))
                    .color(TextColor.fromHexString("#ffffff"))
                    .font(Namespaces.Fonts.VANILLA_CONTAINERS)
                    .append(event.getView()
                            .title()
                            .font(Namespaces.Fonts.DEFAULT)
                            .color(TextColor.fromHexString("#373737")));
            event.titleOverride(title);
        }
    }
}
