package com.example.service;

import com.example.core.command.CommandOutput;
import com.example.core.command.TerminalCommand;
import com.example.core.command.concrete.InvalidCommand;
import com.example.service.registry.CommandRegistry;
import com.example.ui.parser.ParsedCommand;

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
        return terminalCommand.run(parsedCommand.args());
    }
}
