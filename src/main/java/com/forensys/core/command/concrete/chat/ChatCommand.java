package com.forensys.core.command.concrete.chat;

import java.util.List;

import com.forensys.core.chat.ChatParser;
import com.forensys.core.chat.ContactList;
import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandMetadata;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.CommandOutputBuilder;
import com.forensys.core.command.TerminalCommand;
import com.forensys.core.context.ApplicationContext;

public class ChatCommand extends TerminalCommand {

    public ChatCommand() {
        super(new CommandMetadata("chat", "opens chat messager", "command that opens the caht message"));
    }

    @Override
    public CommandOutput run(List<String> args) {
        CommandOutputBuilder outputBuilder = new CommandOutputBuilder();
        
        if (args.isEmpty()) {
            return outputBuilder.text("No arguments passed to chat command, command requires a argument").exitCode(CommandExitCode.FAILURE).build();
        }

        ContactList contactList = ChatParser.getInstance().parse(args.getFirst());

        if (contactList == null) {
            outputBuilder.text("Contact list not found").exitCode(CommandExitCode.FAILURE);
        } else {
            outputBuilder.text("Opening contact list...").exitCode(CommandExitCode.SUCCESS);
            ApplicationContext.getInstance().openContactList(contactList);
        }
        return outputBuilder.build();
    }

}
