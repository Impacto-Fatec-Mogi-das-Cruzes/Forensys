package com.forensys.core.command;

import java.util.ArrayList;
import java.util.List;

public class CommandOutput {
    private List<OutputSegment> segments;
    private CommandExitCode exitCode;
    private boolean clearScreen;

    protected CommandOutput(List<OutputSegment> segments, CommandExitCode exitCode, boolean clearScreen) {
        this.segments = segments;
        this.exitCode = exitCode;
        this.clearScreen = clearScreen;
    }

    public List<OutputSegment> getSegments() {
        return new ArrayList<OutputSegment>(segments);
    }

    public CommandExitCode getExitCode() {
        return exitCode;
    }

    public boolean doesClearScreen() {
        return clearScreen;
    }
}
