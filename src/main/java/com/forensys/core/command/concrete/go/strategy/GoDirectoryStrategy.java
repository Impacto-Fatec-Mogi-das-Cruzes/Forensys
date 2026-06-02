package com.forensys.core.command.concrete.go.strategy;

import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.concrete.go.GoExecutionContext;
import com.forensys.core.context.ApplicationContext;
import com.forensys.core.context.PendingOperation;
import com.forensys.core.filestructure.concrete.Folder;

public class GoDirectoryStrategy implements GoStrategy {

    private final String target;
    private ApplicationContext context;

    public GoDirectoryStrategy(String target) {
        this.target = target;
        context = ApplicationContext.getInstance();
    }

    @Override
    public CommandOutput execute() {
        Folder next = null;

        for (Folder child : context.getCurrentDirectory().getDirectories()) {
            if (child.getMetadata().name().equals(target)) {
                next = child;
                break;
            }
        }

        if (next == null) {
            context.clearAllExecution();
            return CommandOutput.builder()
                .styledText("Directory not found: " + target, "#ef4444")
                .exitCode(CommandExitCode.FAILURE)
                .build();
        }

        if (next.getMetadata().blocked()) {
            requestPassword(next);
            return CommandOutput.builder()
                .styledText("Directory " + next.getMetadata().name() + " is blocked, plase type the password to continue...", "#38bdf8")
                .exitCode(CommandExitCode.PAUSE)
                .build();
        }

        return enterDirectory(next);
    }

    public void requestPassword(Folder next) {
        context.setPendingOperation(new PendingOperation((password) -> {
            if (!(next.getMetadata().password().equals(password))) {
                context.clearAllExecution();
                return CommandOutput.builder()
                        .styledText("Wrong password for " + next.getMetadata().name(), "#ef4444")
                        .exitCode(CommandExitCode.FAILURE)
                        .build();
            }

            ((GoExecutionContext) context.getExecutionContext()).incrementIndex();
            return enterDirectory(next);
        }));
    }

    public CommandOutput enterDirectory(Folder next) {
        context.setCurrentDirectory(next);
        ((GoExecutionContext) context.getExecutionContext()).incrementIndex();
        return CommandOutput.builder()
            .styledText("Moved to Directory: " + next.getMetadata().name(), "#cbd5e1")
            .exitCode(CommandExitCode.SUCCESS)
            .build();
    }
}