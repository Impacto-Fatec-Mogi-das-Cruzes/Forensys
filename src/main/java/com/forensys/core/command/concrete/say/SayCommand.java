package com.forensys.core.command.concrete.say;

import java.util.List;

import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandMetadata;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.CommandOutputBuilder;
import com.forensys.core.command.TerminalCommand;

public class SayCommand extends TerminalCommand {

    public SayCommand() {
        super(new CommandMetadata("say", "Make your terminal say something", "Using this command you can make the terminal say something by doing 'say {text}'"));
    }

    @Override
    public CommandOutput run(List<String> args) {
        CommandOutputBuilder outputBuilder = new CommandOutputBuilder();

        String phrase = "";
        for (String arg : args) {
            phrase += arg + " ";
        }
        
        outputBuilder.text(phrase).exitCode(CommandExitCode.SUCESS);
        return outputBuilder.build();
    }
}
