package com.example.service;

import com.example.common.Registry;
import com.example.core.command.TerminalCommand;

public class CommandRegistry extends Registry<TerminalCommand> {
    private static final CommandRegistry instance = new CommandRegistry();
    
    private CommandRegistry() {
        super();
    }

    public static CommandRegistry getInstance() {
        return instance;
    }
}
