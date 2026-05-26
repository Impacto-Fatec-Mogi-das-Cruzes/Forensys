package com.forensys.service.terminal;

import com.forensys.core.context.ApplicationContext;
import com.forensys.core.context.PendingOperation;

public class GetPendingOperation {
    public static PendingOperation execute() {
        return ApplicationContext.getInstance().getPendingOperation();
    }
}
