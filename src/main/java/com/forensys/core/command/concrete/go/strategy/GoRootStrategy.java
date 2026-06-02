package com.forensys.core.command.concrete.go.strategy;

import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.concrete.go.GoExecutionContext;
import com.forensys.core.context.ApplicationContext;

public class GoRootStrategy implements GoStrategy {

    @Override
    public CommandOutput execute() {
        ApplicationContext context = ApplicationContext.getInstance();

        context.returnRootDirectory();

        ((GoExecutionContext) context.getExecutionContext()).incrementIndex();
        return CommandOutput.builder()
                .styledText("Returned to root directory", "#cbd5e1")
                .exitCode(CommandExitCode.SUCCESS)
                .build();
    }
}