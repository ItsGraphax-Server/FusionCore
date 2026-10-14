package de.itsgraphax.fusion.engine.gui.test;

import de.itsgraphax.fusion.engine.gui.ClickContext;
import de.itsgraphax.fusion.engine.gui.item.GuiItem;
import de.itsgraphax.fusion.engine.gui.session.GuiSession;
import de.itsgraphax.fusion.engine.gui.session.SessionDataKey;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class TestHelloButton implements GuiItem {
    private static final SessionDataKey<Integer> countKey = SessionDataKey.of("count", Integer.class);

    @Override
    public @NotNull ItemStack getItem() {
        return ItemStack.of(Material.DIAMOND);
    }

    @Override
    public void onClick(ClickContext ctx) {
        GuiSession session = ctx.session();

        int count = session.getData().get(TestHelloButton.countKey, 0) + 1;
        session.getData().set(TestHelloButton.countKey, count);

        session.getPlayer().sendMessage(String.format("Count: %s, Slot %s", count, ctx.event().getRawSlot()));
    }
}
