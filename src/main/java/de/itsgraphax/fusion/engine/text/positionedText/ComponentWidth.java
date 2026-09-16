package de.itsgraphax.fusion.engine.text.positionedText;

import de.itsgraphax.fusion.engine.misc.Namespaces;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ObjectComponent;
import net.kyori.adventure.text.TextComponent;

import java.util.Collection;
import java.util.Set;

public class ComponentWidth {
    /**
     * Currently implemented: ASCII chars defined in minecraft:default until (non inclusive) £
     */
    private static int calculateDefaultCharWidth(char c) {
        return switch (c) {
            case 'i', '!', '.',',' , '\'', '|', ':', ';' -> 1;

            case 'l', '`' -> 2;

            case 't', 'I', ' ', '*', '(', ')', '[', ']', '{', '}', '"' -> 3;

            case 'f', 'k', '<', '>' -> 4; // space is subtracted by 1 (4-1=3) because spaces dont have padding

            case '~', '@' -> 6;

            default -> 5;
        };
    }

    private static int calculateDefaultTextWidth(String text) {
        int result = 0;

        for (char c : text.toCharArray()) {
            result += calculateDefaultCharWidth(c);
            result += 1; // padding
        }

        return result; // ignore last padding apparently not
    }


    private static int calculateOffsetTextWidth(String text) {
        int result = 0;

        for (char c : text.toCharArray()) {
            if ((int) c >= OffsetFontOptions.options.size()) throw new RuntimeException(String
                    .format("Tried to get size of invalid offset char: int %s", (int) c));

            result += OffsetFontOptions.options.get(c);
        }

        return result;
    }


    private static int calculateTextComponentWidth(TextComponent component) {
        Key font = component.font();
        if (font == null || font == Namespaces.Fonts.DEFAULT) {
            return calculateDefaultTextWidth(component.content());
        } else if (font.equals(Namespaces.Fonts.OFFSET)) {
            return calculateOffsetTextWidth(component.content());
        } else {
            throw new RuntimeException(String.format("Font \"%s\" unknown", font));
        }
    }


    public static int calculateSingleComponentWidth(Component component) {
        int result = 0;

        // pertype calculation
        if (component instanceof TextComponent textComponent) {
            result += calculateTextComponentWidth(textComponent);
        } else if (component instanceof ObjectComponent) {
            result += 6; // Object components are always 8x8 pixels but for some reason -2 cause why not (apparently no spacing)
        } else {
            throw new RuntimeException(String.format("Components of type %s are not supported due to them not being used in plugin contexts", component.getClass().getCanonicalName()));
        }

        // calculate children
        result += calculateMultipleComopnentsWidth(component.children());

        return result + 1; // padding between components
    }

    private static int calculateMultipleComopnentsWidth(Collection<Component> components) {
        int result = 0;
        for (Component component : components) {
            result += calculateSingleComponentWidth(component);
        }
        return result;
    }

    public static int calculateWidth(Component component) {
        return calculateWidth(Set.of(component));
    }

    public static int calculateWidth(Collection<Component> components) {
        return calculateMultipleComopnentsWidth(components) - 1; // -1 due to padding not applying to last letter
    }
}
