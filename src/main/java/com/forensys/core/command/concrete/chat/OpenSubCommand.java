package com.forensys.core.command.concrete.chat;

import com.forensys.core.chat.ChatParser;
import com.forensys.core.chat.ContactList;
import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.ParsedCommandArgs;
import com.forensys.core.command.SegmentStyle;
import com.forensys.core.context.ApplicationContext;

public class OpenSubCommand implements ChatSubCommand {

    @Override
    public CommandOutput execute(ParsedCommandArgs args) {
        ContactList contactList = ChatParser.getInstance().parse(args.positionals().get(1));

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
