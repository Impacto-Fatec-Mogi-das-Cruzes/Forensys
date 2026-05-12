package com.forensys.core.command.concrete.clear;

import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandMetadata;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.CommandOutputBuilder;
import com.forensys.core.command.TerminalCommand;
import com.forensys.ui.command.ParsedCommandArgs;

public class ClearCommand extends TerminalCommand {

    public ClearCommand() {
        super(new CommandMetadata("clear", "clear the screen of the current output", "clears the screen"));
    }

    @Override
    public CommandOutput run(ParsedCommandArgs args) {
        CommandOutputBuilder outputBuilder = new CommandOutputBuilder();
        if (!args.positionals().isEmpty()) {
            return outputBuilder.text("Command clear does not accept arguments").exitCode(CommandExitCode.FAILURE).build();
        }
        return outputBuilder.clearScreen(true).exitCode(CommandExitCode.SUCCESS).build();
    }

}
