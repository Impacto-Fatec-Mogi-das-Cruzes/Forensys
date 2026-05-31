package com.forensys.core.command.concrete.help;

import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.CommandOutputBuilder;
import com.forensys.core.command.CommandRegistry;
import com.forensys.core.command.TerminalCommand;

public class GeneralMessageStrategy implements MessageStrategy {

    @Override
    public CommandOutput build() {
        CommandOutputBuilder outputBuilder = CommandOutput.builder();
        
        outputBuilder.styledText("Help for commands:", "#cbd5e1").newLine();
        for (TerminalCommand command : CommandRegistry.getInstance().getAll().values()) {
            outputBuilder.styledText("\t" + command.getCommandName() + "\t" + command.getHelpMessage(), "#cbd5e1").newLine();
        }
        
        return outputBuilder.exitCode(CommandExitCode.SUCCESS).build();
    }
}
