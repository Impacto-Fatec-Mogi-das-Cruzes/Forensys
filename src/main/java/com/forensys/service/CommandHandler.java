package com.forensys.service;

import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.CommandRegistry;
import com.forensys.core.command.ParsedCommand;
import com.forensys.core.command.TerminalCommand;
import com.forensys.core.command.concrete.invalid.InvalidCommand;

// Takes a parsed command from the controller, runs it, check the result and tells back the controller what 
public class CommandHandler {
    private static CommandHandler instance;
    
    private CommandHandler() {}
    
    public static CommandHandler getInstance() {
        if (CommandHandler.instance == null) {
            instance = new CommandHandler();
        }
        return instance;
    }

    public CommandOutput handle(ParsedCommand parsedCommand) {
        CommandRegistry commandRegistry = CommandRegistry.getInstance();
        TerminalCommand terminalCommand = commandRegistry.get(parsedCommand.command());
        if (terminalCommand == null) {
            terminalCommand = new InvalidCommand();
        }
        return terminalCommand.run(
            parsedCommand.arguments()
        );
    }
}
