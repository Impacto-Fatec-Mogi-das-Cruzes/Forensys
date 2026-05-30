package com.forensys.core.command.concrete.chat;

import com.forensys.core.chat.ChatParser;
import com.forensys.core.chat.ContactList;
import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandMetadata;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.ParsedCommandArgs;
import com.forensys.core.command.SegmentStyle;
import com.forensys.core.command.TerminalCommand;
import com.forensys.core.context.ApplicationContext;

public class ChatCommand extends TerminalCommand {

    public ChatCommand() {
        super(new CommandMetadata("chat", "opens chat messager", "command that opens the caht message"));
    }

    @Override
    public CommandOutput execute(ParsedCommandArgs args) {
        if (args.positionals().isEmpty()) {
            return CommandOutput.builder()
                .styledText("No arguments passed to chat command, command requires a argument", "#ef4444", SegmentStyle.BOLD)
                .exitCode(CommandExitCode.FAILURE)
                .build();
        }

        ContactList contactList = ChatParser.getInstance().parse(args.positionals().getFirst());

        if (contactList == null) {
            return CommandOutput.builder()
                .styledText("Contact list not found", "#ef4444", SegmentStyle.BOLD)
                .exitCode(CommandExitCode.FAILURE)
                .build();
        }
        
        ApplicationContext.getInstance().openContactList(contactList);
        return CommandOutput.builder()
            .styledText("Opening contact list...", "#38bdf8", SegmentStyle.BOLD)
            .exitCode(CommandExitCode.SUCCESS)
            .build();
    }

}
