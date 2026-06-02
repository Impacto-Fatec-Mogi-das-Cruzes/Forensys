package com.forensys.core.command.concrete.chat;

import com.forensys.core.chat.Contact;
import com.forensys.core.chat.ContactParser;
import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.ParsedCommandArgs;
import com.forensys.core.command.SegmentStyle;
import com.forensys.core.context.ApplicationContext;
import com.forensys.core.filestructure.concrete.ChatFile;

public class RegisterSubCommand implements ChatSubCommand {

    @Override
    public CommandOutput execute(ParsedCommandArgs args) {
        
        if (args.positionals().size() < 2) {
            return CommandOutput.builder()
                .styledText("Too few arguments passed for chat command register, one argument is required", "#ef4444", SegmentStyle.BOLD)
                .exitCode(CommandExitCode.FAILURE)
                .build();
        }

        ApplicationContext applicationContext = ApplicationContext.getInstance();

        ChatFile entry = null;
        for (ChatFile chat : applicationContext.getCurrentDirectory().getChildrenOfType(ChatFile.class)) {
            if (chat.getMetadata().name().equals(args.positionals().get(1))) {
                entry = chat;
                break;
            }
        }

        if (entry == null) {
            return CommandOutput.builder()
                .styledText("Contact no found", "#ef4444", SegmentStyle.BOLD)
                .exitCode(CommandExitCode.FAILURE)
                .build();
        }

        System.out.println(entry.getContent());
        Contact contact = ContactParser.getInstance().parse(entry.getContent());

        if (contact == null) {
            return CommandOutput.builder()
                .styledText("Contact not found", "#ef4444", SegmentStyle.BOLD)
                .exitCode(CommandExitCode.FAILURE)
                .build();
        }
        applicationContext.getContactList().addContact(contact);

        return CommandOutput.builder()
            .styledText("Sucessufuly registred new contact to contact list...", "#38bdf8", SegmentStyle.BOLD)
            .exitCode(CommandExitCode.SUCCESS)
            .build();
    }

}
