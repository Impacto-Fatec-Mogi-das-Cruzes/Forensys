package com.forensys.core.command.concrete.go.strategy;

import com.forensys.common.command.ExecutionStrategy;
import com.forensys.common.exception.InvalidDirectoryMovement;
import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.ParsedCommandArgs;
import com.forensys.core.context.ApplicationContext;

public class GoBackStrategy implements ExecutionStrategy {

    @Override
    public CommandOutput execute(ParsedCommandArgs arguments) {
        ApplicationContext applicationContext = ApplicationContext.getInstance();
        try {
            applicationContext.restoreDirectory();
        } catch (InvalidDirectoryMovement e) {
            return CommandOutput.builder()
                    .text(e.getMessage())
                    .exitCode(CommandExitCode.FAILURE)
                    .build();
        } catch (Exception e) {
            System.err.println(e);
        }
        return CommandOutput.builder()
                .text("Going back to " + applicationContext.getCurrentDirectory().getMetadata().name())
                .exitCode(CommandExitCode.SUCCESS)
                .build();
    }

}
