package com.forensys.core.command.concrete.invalid;

import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandMetadata;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.CommandOutputBuilder;
import com.forensys.core.command.ParsedCommandArgs;
import com.forensys.core.command.TerminalCommand;

public class InvalidCommand extends TerminalCommand {

    public InvalidCommand() {
        super(new CommandMetadata("", "", ""));
    }

    @Override
    public CommandOutput run(ParsedCommandArgs args) {
        CommandOutputBuilder outputBuilder = new CommandOutputBuilder();
        
        outputBuilder
            .text("Invalid Command, plase input a valid command")
            .exitCode(CommandExitCode.FAILURE);
            
        return outputBuilder.build();
    }

}
