package com.forensys.service.terminal;

import com.forensys.core.command.CommandRegistry;
import com.forensys.core.command.TerminalCommand;
import com.forensys.core.command.concrete.chat.ChatCommand;
import com.forensys.core.command.concrete.clear.ClearCommand;
import com.forensys.core.command.concrete.duck.DuckCommand;
import com.forensys.core.command.concrete.go.GoCommand;
import com.forensys.core.command.concrete.help.HelpCommand;
import com.forensys.core.command.concrete.list.ListCommand;
import com.forensys.core.command.concrete.read.ReadCommand;
import com.forensys.core.command.concrete.say.SayCommand;
import com.forensys.core.command.concrete.view.ViewCommand;

public class RegisterAllCommands {

    public static void execute() {
        TerminalCommand[] commands = new TerminalCommand[] {
            new DuckCommand(),
            new SayCommand(),
            new GoCommand(),
            new ListCommand(),
            new ReadCommand(),
            new ChatCommand(),
            new HelpCommand(),
            new ClearCommand(),
            new ViewCommand()
        };
        CommandRegistry commandRegistry = CommandRegistry.getInstance();
        for (TerminalCommand terminalCommand : commands) {
            commandRegistry.register(terminalCommand.getCommandName(), terminalCommand);
        }
    }
}
