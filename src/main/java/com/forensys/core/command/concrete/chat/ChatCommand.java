package com.forensys.core.command.concrete.chat;

import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandMetadata;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.ParsedCommandArgs;
import com.forensys.core.command.SegmentStyle;
import com.forensys.core.command.TerminalCommand;

public class ChatCommand extends TerminalCommand {

    public ChatCommand() {
        super(new CommandMetadata(
        "chat",
        "Manage and navigate chat contacts and conversations",
        """
        Usage:
        chat list {pattern}
        chat open {contactlist}
        
        Examples:
        chat list john
        chat list *
        chat open contacts
        chat open favorites
        
        Notes:
        - Use pattern matching to search contacts by name
        - Use * to display all available contacts
        - Contact lists must be created before opening
        - Supports wildcard patterns for flexible searching
        """));

    }

    @Override
    public CommandOutput execute(ParsedCommandArgs args) {
        if (args.positionals().isEmpty()) {
            return CommandOutput.builder()
                .styledText("No arguments passed to chat command, command requires a argument", "#ef4444", SegmentStyle.BOLD)
                .exitCode(CommandExitCode.FAILURE)
                .build();
        }

        ChatSubCommand command = ChatSubCommandFactory.createSubCommand(args);

        if (command == null) {
            return CommandOutput.builder()
                .styledText("Unkown argument", "#ef4444", SegmentStyle.BOLD)
                .exitCode(CommandExitCode.FAILURE)
                .build();
        }
        
        return command.execute(args);
    }

}
