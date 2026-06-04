package com.forensys.core.command;

public enum SegmentDecoration {
    BOLD("bold"),
    ITALIC("italic"),
    UNDERLINE("underline"),
    STRIKE("strike"),
    DIM("dim");

    private final String styleClass;

    SegmentDecoration(String styleClass) {
        this.styleClass = styleClass;
    }

    public String styleClass() {
        return this.styleClass;
    }

}
