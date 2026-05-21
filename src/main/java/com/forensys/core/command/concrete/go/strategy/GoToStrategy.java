package com.forensys.core.command.concrete.go.strategy;

import com.forensys.common.command.ExecutionStrategy;
import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.ParsedCommandArgs;
import com.forensys.core.context.ApplicationContext;
import com.forensys.core.filestructure.concrete.Directory;

public class GoToStrategy implements ExecutionStrategy {

    @Override
    public CommandOutput execute(ParsedCommandArgs args) {

        String[] targets = args.positionals().getFirst().split("/");

        CommandOutput output = null;

        for (String target : targets) {
            output = resolve(target);
        }

        return output;
    }

    private CommandOutput resolve(String target) {
        if (target.equals("$root")) {
            return resolveRoot();
        }

        if (target.equals("$parent")) {
            return resolveParent();
        }

        return resolveDirectory(target);
    }

    private CommandOutput resolveRoot() {
        ApplicationContext context = ApplicationContext.getInstance();
        while (true) {
            try {
                context.restoreDirectory();
            } catch (Exception e) {
                break;
            }
        }

        return CommandOutput.builder()
                .text("Returned to root directory")
                .exitCode(CommandExitCode.SUCCESS)
                .build();
    }

    private CommandOutput resolveParent() {
        ApplicationContext context = ApplicationContext.getInstance();

        context.restoreDirectory();

        return CommandOutput.builder()
                .text("Moved to parent directory")
                .exitCode(CommandExitCode.SUCCESS)
                .build();
    }

    private CommandOutput resolveDirectory(String target) {
        ApplicationContext context = ApplicationContext.getInstance();

        Directory current = context.getCurrentDirectory();
        Directory next = null;
        for (Directory child : current.getDirectories()) {
            if (child.getMetadata().name().equals(target)) {
                next = child;
                break;
            }
        }

        if (next == null) {
            return CommandOutput.builder()
                    .text("Directory not found: " + target)
                    .exitCode(CommandExitCode.FAILURE)
                    .build();
        }

        context.setCurrentDirectory(next);

        return CommandOutput.builder()
                .text("Entered directory: " + next.getMetadata().name())
                .exitCode(CommandExitCode.SUCCESS)
                .build();
    }
}