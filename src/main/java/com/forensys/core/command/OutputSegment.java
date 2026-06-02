package com.forensys.core.command;

import com.forensys.common.HexColor;

public final class OutputSegment {
    private final String text;
    private final SegmentStyle[] styles;
    private final HexColor color;

    public OutputSegment(String text, String color, SegmentStyle... styles) {
        this.text = text;
        this.color = HexColor.of(color);
        this.styles = styles;
    }

    public String getText() {
        return text;
    }

    public SegmentStyle[] getStyles() {
        return styles;
    }

    public String getColor() {
        return color.value();
    }

}
