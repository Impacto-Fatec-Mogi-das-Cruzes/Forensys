package com.forensys.core.command.concrete.go.strategy.GoDirectory;

import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.concrete.go.strategy.GoStrategy;
import com.forensys.core.command.concrete.go.strategy.GoDirectory.helper.EnterDirectory;
import com.forensys.core.command.concrete.go.strategy.GoDirectory.helper.FindDirectory;
import com.forensys.core.command.concrete.go.strategy.GoDirectory.helper.RequestPassword;
import com.forensys.core.filestructure.concrete.Directory;

public class GoDirectoryStrategy implements GoStrategy {

    private final String target;

    public GoDirectoryStrategy(String target) {
        this.target = target;
    }

    @Override
    public CommandOutput execute() {
        Directory next = FindDirectory.execute(target);

        if (next == null) {
            return CommandOutput.builder()
                .styledText("Directory not found: " + target, "#ef4444")
                .exitCode(CommandExitCode.FAILURE)
                .build();
        }

        if (next.getMetadata().blocked()) {
            RequestPassword.execute(next);
            return CommandOutput.builder()
                .styledText("Directory " + next.getMetadata().name() + " is blocked, plase type the password to continue...", "#38bdf8")
                .exitCode(CommandExitCode.PAUSE)
                .build();
        }

        return EnterDirectory.execute(next);
    }
}