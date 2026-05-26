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
                    .text("Returned to root directory")
                    .exitCode(CommandExitCode.SUCCESS)
                    .build();
        } catch (Exception e) {
            return CommandOutput.builder()
                    .text("UnknownError")
                    .exitCode(CommandExitCode.FAILURE)
                    .build();
        }
    }
}