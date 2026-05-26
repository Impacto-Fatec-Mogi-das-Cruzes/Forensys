package com.forensys.service.terminal;

import com.forensys.core.command.CommandOutput;
import com.forensys.core.context.ApplicationContext;
import com.forensys.core.context.PendingExecution;

public class ContinueExecution {
    public static CommandOutput execute() {
        PendingExecution pendingExecution = ApplicationContext.getInstance().getPendingExecution();
        return pendingExecution.callback().get();
    }
}
