package de.itsgraphax.fusion.engine.text.positionedText;

import de.itsgraphax.fusion.engine.misc.Namespaces;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public final class Offset {
    public static int getOffset(char c) {
        return OffsetFontOptions.options.get(c);
    }

    private static @NotNull Map<Integer, Integer> buildOffsetTree(int offset) {
        // build tree
        // this goes through every option of addition and builds a tree until it comes to the target value
        // code slightly copied from wikipedia
        // this always finds the shortest as it stops on the lowest found depth

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        Map<Integer, Integer> nextToCurrent = new HashMap<>();

        queue.add(0);

        while (!queue.isEmpty()) {
            var current = queue.removeFirst();

            for (var offsetOption : OffsetFontOptions.options) {
                // after the addition
                var next = current + offsetOption;

                // already explored
                if (nextToCurrent.containsKey(next)) continue;

                // explore
                nextToCurrent.put(next, current);

                // found target
                if (next == offset) {
                    queue.clear();
                    break;
                }
                // mark as to explore later
                queue.add(next);
            }
        }
        return nextToCurrent;
    }

    /**
     * Create a component which offsets the text
     *
     * @param offset The amount of steps to offset
     * @return A component offsetting the following text by offset
     */
    public static @NotNull Component createOffset(int offset) {
        Map<Integer, Integer> nextToCurrent = buildOffsetTree(offset);

        // build string
        // this explores the tree backwards until it finds the final value
        var result = new StringBuilder();
        var current = offset;

        while (current != 0) {
            var previous = nextToCurrent.get(current);
            var offsetOption = current - previous;

            result.append((char) OffsetFontOptions.options.indexOf(offsetOption));
            current = previous;
        }

        return Component.text(result.toString()).font(Namespaces.Fonts.OFFSET);
    }

}
