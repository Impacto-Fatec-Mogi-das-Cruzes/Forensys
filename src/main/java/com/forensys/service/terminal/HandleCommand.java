package com.forensys.service.terminal;

import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.CommandRegistry;
import com.forensys.core.command.ParsedCommand;
import com.forensys.core.command.TerminalCommand;
import com.forensys.core.command.concrete.invalid.InvalidCommand;

public class HandleCommand {
    public static CommandOutput execute(ParsedCommand parsedCommand) {
        CommandRegistry commandRegistry = CommandRegistry.getInstance();
        TerminalCommand terminalCommand = commandRegistry.get(parsedCommand.command());
        return (terminalCommand != null ? terminalCommand : new InvalidCommand()).execute(
            parsedCommand.arguments()
        );
    }
}
