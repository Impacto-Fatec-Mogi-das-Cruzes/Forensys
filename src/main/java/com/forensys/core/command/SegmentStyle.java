package com.forensys.core.command;

public enum SegmentStyle {
    BOLD("bold"),
    ITALIC("italic"),
    UNDERLINE("underline"),
    STRIKE("strike"),
    DIM("dim");

    private final String styleClass;

    SegmentStyle(String styleClass) {
        this.styleClass = styleClass;
    }

    public String styleClass() {
        return this.styleClass;
    }

}
