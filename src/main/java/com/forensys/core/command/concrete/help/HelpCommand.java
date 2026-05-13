package com.forensys.core.command.concrete.help;

import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandMetadata;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.CommandOutputBuilder;
import com.forensys.core.command.CommandRegistry;
import com.forensys.core.command.ParsedCommandArgs;
import com.forensys.core.command.TerminalCommand;

public class HelpCommand extends TerminalCommand {

    public HelpCommand() {
        super(new CommandMetadata("help", "exibts help message", "help for commands"));
    }

    @Override
    public CommandOutput run(ParsedCommandArgs args) {
        CommandOutputBuilder outputBuilder = new CommandOutputBuilder();

        if (args.positionals().isEmpty()) {
            outputBuilder.text("Help for commands");
            for (TerminalCommand command : CommandRegistry.getInstance().getAll().values()) {
                outputBuilder.text("\t" + command.getCommandName() + "\t" + command.getHelpMessage()).newLine();
            }
            outputBuilder.exitCode(CommandExitCode.SUCCESS);
        } else {
            TerminalCommand command = CommandRegistry.getInstance().get(args.positionals().getFirst());
            outputBuilder.text("Command " + command.getCommandName() + " does:").newLine();
            outputBuilder.text("\t" + command.getCommandName() + "\t" + command.getDescription()).newLine();
        }

        return outputBuilder.build();
    }

}
