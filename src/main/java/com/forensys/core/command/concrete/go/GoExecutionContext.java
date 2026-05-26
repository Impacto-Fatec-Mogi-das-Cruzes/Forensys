package com.forensys.core.command.concrete.go;

import com.forensys.core.context.ExecutionContext;

public class GoExecutionContext implements ExecutionContext {
    private String[] targets;
    private int currentIndex;
    // private CommandOutput output;

    public GoExecutionContext(String[] targets, int currentIndex) {
        this.targets = targets;
        this.currentIndex = currentIndex;
    }

    public String[] getTargets() {
        return targets;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setIndex(int i) {
        this.currentIndex = i;
    }

    // public CommandOutput getOutput() {
    //     return output;
    // }

    // public void setOutput(CommandOutput output) {
    //     this.output = output;
    // }
}