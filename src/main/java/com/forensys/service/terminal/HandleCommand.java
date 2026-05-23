package com.forensys.service.terminal;

import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.CommandRegistry;
import com.forensys.core.command.ParsedCommand;
import com.forensys.core.command.TerminalCommand;
import com.forensys.core.command.concrete.invalid.InvalidCommand;

public class HandleCommand {
    public static CommandOutput execute(ParsedCommand parsedCommand) {
        System.out.println(parsedCommand.toString());

        CommandRegistry commandRegistry = CommandRegistry.getInstance();
        TerminalCommand terminalCommand = commandRegistry.get(parsedCommand.command());
        if (terminalCommand == null) {
            terminalCommand = new InvalidCommand();
        }
        return terminalCommand.execute(
            parsedCommand.arguments()
        );
    }
}
