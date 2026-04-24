package com.forensys.core.command;

import java.util.ArrayList;
import java.util.List;

public class CommandOutputBuilder {
    private List<String> segments = new ArrayList<>();
    private CommandExitCode exitCode = CommandExitCode.SUCESS;
    private boolean clearScreen = false;

    public CommandOutputBuilder text(String text) {
        segments.add(text);
        return this;
    }

    public CommandOutputBuilder newLine() {
        segments.add("\n");
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
