package de.itsgraphax.fusion.engine.gui.test;

import de.itsgraphax.fusion.engine.gui.Gui;
import de.itsgraphax.fusion.engine.gui.session.SessionItems;
import net.kyori.adventure.text.Component;

public class TestGui extends Gui {
    public TestGui() {
        super(18, Component.text("hello"), true);
    }

    @Override
    public void onFirstRender(SessionItems items) {
        items.setItem(10, new TestHelloButton());
        items.setHotbarItem(3, new TestHelloButton());
        items.setInventoryItem(13, new TestHelloButton());
    }
}
