package de.itsgraphax.fusion.engine.text.positionedText;

import java.util.List;

public interface OffsetFontOptions {
    List<Integer> options = List.of(
            -1, -2, -4, -8, -16, -32, -64, -128, -256, -512,
            1, 2, 4, 8, 16, 32, 64, 128, 256, 512
    );
}
