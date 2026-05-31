package com.forensys.core.command.concrete.go.strategy.GoDirectory.helper;

import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.context.ApplicationContext;
import com.forensys.core.filestructure.concrete.Directory;

public class EnterDirectory {
    public static CommandOutput execute(Directory next) {
        ApplicationContext.getInstance().setCurrentDirectory(next);
        return CommandOutput.builder()
            .styledText("Moved to Directory: " + next.getMetadata().name(), "#cbd5e1")
            .exitCode(CommandExitCode.SUCCESS)
            .build();
    }
}
