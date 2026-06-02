package com.forensys.core.command.concrete.go;

import java.util.List;

import com.forensys.core.context.ExecutionContext;

public class GoExecutionContext implements ExecutionContext {
    private final List<String> targets;
    private int currentIndex;

    public GoExecutionContext(List<String> targets, int currentIndex) {
        this.targets = targets;
        this.currentIndex = currentIndex;
    }

    public List<String> getTargets() {
        return List.copyOf(targets);
    }

    public boolean hasTargetsLeft() {
        return currentIndex < targets.size();
    }

    public void incrementIndex() {
        if (hasTargetsLeft()) {
            currentIndex++;
        }
    }

    public String getCurrentTarget() {
        if (!hasTargetsLeft()) {
            throw new IllegalStateException("No targets remaining");
        }
        return targets.get(currentIndex);
    }
}