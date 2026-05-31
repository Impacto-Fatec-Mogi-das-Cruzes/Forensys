package com.forensys.core.command.concrete.help;

import com.forensys.core.command.CommandMetadata;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.ParsedCommandArgs;
import com.forensys.core.command.TerminalCommand;

public class HelpCommand extends TerminalCommand {

    public HelpCommand() {
        super(new CommandMetadata(
        "help",
        "Display help information for commands",
        """
        Usage:
        help
        help {command}

        Examples:
        help
        help chat
        help clear
        help duck

        Notes:
        - Running 'help' displays all available commands
        - Running 'help {command}' displays detailed information about a specific command
        - Includes command descriptions, usage examples, and additional notes when available
        """
        ));
    }

    @Override
    public CommandOutput execute(ParsedCommandArgs args) {
        MessageStrategy strategy = HelpStrategyFactory.createMessageBuilderStrategy(args);
        return strategy.build();
    }

}
