package com.forensys.common.command;

import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.ParsedCommandArgs;

public class ExecutionDecorator implements ExecutionStrategy {
    
    private ExecutionStrategy wrapped = null;

    public ExecutionDecorator(ExecutionStrategy wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public CommandOutput execute(ParsedCommandArgs arguments) {
        return wrapped.execute(arguments);
    }

}
