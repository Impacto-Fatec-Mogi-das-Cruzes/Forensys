package com.forensys.core.setting;

import javafx.stage.StageStyle;

public record Window (
    String title,
    String icon,
    StageStyle style,
    boolean resizable,
    boolean fullscreen,
    int width,
    int height
) {}