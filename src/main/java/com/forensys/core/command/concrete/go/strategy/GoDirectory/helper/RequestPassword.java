package com.forensys.core.command.concrete.go.strategy.GoDirectory.helper;

import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.concrete.go.GoExecutionContext;
import com.forensys.core.context.ApplicationContext;
import com.forensys.core.context.PendingOperation;
import com.forensys.core.filestructure.concrete.Directory;

public class RequestPassword {
    public static void execute(Directory next) {
        ApplicationContext.getInstance().setPendingOperation(new PendingOperation((password) -> {
            if (!(next.getMetadata().password().equals(password))) {
                ApplicationContext.getInstance().clearPendingExecution();
                ApplicationContext.getInstance().clearExecutionContext();
                ApplicationContext.getInstance().clearPendingOperation();
                return CommandOutput.builder()
                        .styledText("Wrong password for " + next.getMetadata().name(), "#ef4444")
                        .exitCode(CommandExitCode.FAILURE)
                        .build();
            }

            GoExecutionContext state = (GoExecutionContext) ApplicationContext.getInstance().getExecutionContext();
            state.setIndex(state.getCurrentIndex() + 1);
            return EnterDirectory.execute(next);
        }));
    }
}
