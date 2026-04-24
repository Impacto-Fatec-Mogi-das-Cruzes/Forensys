package com.forensys.core.command;

import java.util.ArrayList;
import java.util.List;

public class CommandOutput {
    private List<String> segments;
    private CommandExitCode exitCode;
    private boolean clearScreen;

    CommandOutput(List<String> segments, CommandExitCode exitCode, boolean clearScreen) {
        this.exitCode = exitCode;
        this.segments = segments;
        this.clearScreen = clearScreen;
    }

    public List<String> getSegments() {
        return new ArrayList<String>(segments);
    }

    public CommandExitCode getExitCode() {
        return exitCode;
    }

    public boolean isClearScreen() {
        return clearScreen;
    }
}
