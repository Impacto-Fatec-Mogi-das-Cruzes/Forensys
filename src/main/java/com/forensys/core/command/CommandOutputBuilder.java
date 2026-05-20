package com.forensys.core.command;

import java.util.ArrayList;
import java.util.List;

public class CommandOutputBuilder {
    private List<OutputSegment> segments = new ArrayList<>();
    private CommandExitCode exitCode = CommandExitCode.SUCCESS;
    private boolean clearScreen = false;

    public CommandOutputBuilder text(String text) {
        segments.add(new OutputSegment(text));
        return this;
    }

    public CommandOutputBuilder styledText(String text, String color, SegmentStyle... styles) {
        segments.add(new OutputSegment(text, color, styles));
        return this;
    }

    public CommandOutputBuilder newLine() {
        segments.add(new OutputSegment("\n"));
        return this;
    }

    public CommandOutputBuilder exitCode(CommandExitCode exitCode) {
        this.exitCode = exitCode;
        return this;
    }
    
    public CommandOutputBuilder clearScreen(boolean clearScreen) {
        this.clearScreen = clearScreen;
        return this;
    }

    public CommandOutput build() {
        return new CommandOutput(segments, exitCode, clearScreen);
    }
}
