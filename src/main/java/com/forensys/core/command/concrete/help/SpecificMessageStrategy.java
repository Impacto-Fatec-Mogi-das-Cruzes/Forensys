package com.forensys.core.command.concrete.help;

import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.CommandOutputBuilder;
import com.forensys.core.command.CommandRegistry;
import com.forensys.core.command.TerminalCommand;

public class SpecificMessageStrategy implements MessageStrategy {

    private TerminalCommand command;

    public SpecificMessageStrategy(String command) {
        this.command = CommandRegistry.getInstance().get(command);
    }

    @Override
    public CommandOutput build() {
        CommandOutputBuilder outputBuilder = CommandOutput.builder();
        
        outputBuilder.styledText("Command " + command.getCommandName() + " does:", "#cbd5e1").newLine();
        
        String[] lines = command.getDescription().split("\n");
        
        for (String line : lines) {
            outputBuilder.text("\t" + line).newLine();
        }

        return outputBuilder.exitCode(CommandExitCode.SUCCESS).build();
    }

}
