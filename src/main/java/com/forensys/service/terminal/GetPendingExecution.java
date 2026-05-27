package com.forensys.service.terminal;

import com.forensys.core.context.ApplicationContext;
import com.forensys.core.context.PendingExecution;

public class GetPendingExecution {
    public static PendingExecution execute() {
        return ApplicationContext.getInstance().getPendingExecution();
    }
}
