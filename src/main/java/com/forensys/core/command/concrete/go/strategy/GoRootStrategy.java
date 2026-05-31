package com.forensys.core.command.concrete.go.strategy;

import com.forensys.common.exception.InvalidDirectoryMovement;
import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.context.ApplicationContext;

public class GoRootStrategy implements GoStrategy {

    @Override
    public CommandOutput execute() {
        ApplicationContext context = ApplicationContext.getInstance();

        try {
            while (true) {
                context.restoreDirectory();
            }
        } catch (InvalidDirectoryMovement e) {
            return CommandOutput.builder()
                    .styledText("Returned to root directory", "#cbd5e1")
                    .exitCode(CommandExitCode.SUCCESS)
                    .build();
        } catch (Exception e) {
            return CommandOutput.builder()
                    .styledText("Failed to go back to root directory", "#ef4444")
                    .exitCode(CommandExitCode.FAILURE)
                    .build();
        }
    }
}