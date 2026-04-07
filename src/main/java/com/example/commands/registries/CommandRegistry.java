package com.example.commands.registries;

import java.util.HashMap;
import java.util.Map;

import com.example.commands.TerminalCommand;

// Registry Pattern
public class CommandRegistry {
    private static final CommandRegistry instance = new CommandRegistry();
    private Map<String, TerminalCommand> commandRegistry;
    
    private CommandRegistry() {
        commandRegistry = new HashMap<>();
    }

    public static CommandRegistry getInstance() {
        return instance;
    }

    public void register(TerminalCommand command) {
        commandRegistry.put(command.getCommandName(), command);
    }

    public TerminalCommand get(String key) {
        return commandRegistry.get(key);
    }
}
