package com.forensys.core.command.concrete.clear;

import java.util.List;

import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandMetadata;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.CommandOutputBuilder;
import com.forensys.core.command.TerminalCommand;

public class ClearCommand extends TerminalCommand {

    public ClearCommand() {
        super(new CommandMetadata("clear", "clear the screen of the current output", "clears the screen"));
    }

    @Override
    public CommandOutput run(List<String> args) {
        CommandOutputBuilder outputBuilder = new CommandOutputBuilder();
        if (!args.isEmpty()) {
            return outputBuilder.text("Command clear does not accept arguments").exitCode(CommandExitCode.FAILURE).build();
        }
        return outputBuilder.clearScreen(true).exitCode(CommandExitCode.SUCCESS).build();
    }

}
