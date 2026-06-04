package com.forensys.core.command.concrete.invalid;

import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandMetadata;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.ParsedCommandArgs;
import com.forensys.core.command.TerminalCommand;

public class InvalidCommand extends TerminalCommand {

    public InvalidCommand() {
        super(new CommandMetadata("", "", ""));
    }

    @Override
    public CommandOutput execute(ParsedCommandArgs args) {
        return CommandOutput.builder()
            .text("Invalid Command, plase input a valid command", "#ef4444")
            .exitCode(CommandExitCode.FAILURE)
            .build();
    }

}
