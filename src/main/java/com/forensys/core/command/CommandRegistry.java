package com.forensys.core.command;

import com.forensys.common.Registry;

public class CommandRegistry extends Registry<TerminalCommand> {
    private static final CommandRegistry instance = new CommandRegistry();
    
    private CommandRegistry() {
        super();
    }

    public static CommandRegistry getInstance() {
        return instance;
    }
}
