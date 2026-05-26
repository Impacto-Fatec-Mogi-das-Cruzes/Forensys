package com.forensys.service.terminal;

import com.forensys.core.command.CommandOutput;
import com.forensys.core.context.ApplicationContext;
import com.forensys.core.context.PendingOperation;

public class HandlePendingOperation {

    public static CommandOutput execute(String input) {

        ApplicationContext context = ApplicationContext.getInstance();

        PendingOperation request = context.getPendingOperation();
        request = context.getPendingOperation();

        context.clearPendingOperation();

        return request.callback().apply(input);
    }
}