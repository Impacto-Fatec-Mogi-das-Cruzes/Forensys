package com.forensys.service;

import com.forensys.core.command.CommandRegistry;
import com.forensys.core.command.TerminalCommand;

public class RegisterAllCommands {

    private TerminalCommand[] commands;

    public RegisterAllCommands(TerminalCommand... commands) {
        this.commands = commands;
    }

    public void execute() {
        CommandRegistry commandRegistry = CommandRegistry.getInstance();
        for (TerminalCommand terminalCommand : commands) {
            commandRegistry.register(terminalCommand.getCommandName(), terminalCommand);
        }
    }
}
