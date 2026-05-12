package com.forensys.core.command.concrete.say;

import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandMetadata;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.CommandOutputBuilder;
import com.forensys.core.command.TerminalCommand;
import com.forensys.ui.command.ParsedCommandArgs;

public class SayCommand extends TerminalCommand {

    public SayCommand() {
        super(new CommandMetadata("say", "Make your terminal say something", "Using this command you can make the terminal say something by doing 'say {text}'"));
    }

    @Override
    public CommandOutput run(ParsedCommandArgs args) {
        CommandOutputBuilder outputBuilder = new CommandOutputBuilder();

        String phrase = "";
        for (String arg : args.positionals()) {
            phrase += arg + " ";
        }
        
        outputBuilder.text(phrase).exitCode(CommandExitCode.SUCCESS);
        return outputBuilder.build();
    }
}
