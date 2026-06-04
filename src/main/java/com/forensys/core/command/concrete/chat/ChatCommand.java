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
            "Manage chat contacts and conversations",
            """
            Usage:
            chat open
            chat register <chat-file>

            Examples:
            chat open
            chat register mother.chat
            chat register chiozano.chat

            Notes:
            - chat open opens the current contact list
            - chat register imports a contact from a ChatFile in the current directory
            - The specified chat file must exist and contain valid contact data
            - Registered contacts become available in the contact list
            """));
    }

    @Override
    public CommandOutput execute(ParsedCommandArgs args) {
        if (args.positionals().isEmpty()) {
            return CommandOutput.builder()
                .text("No arguments passed to chat command, command requires at least one argument", "#ef4444", SegmentStyle.BOLD)
                .exitCode(CommandExitCode.FAILURE)
                .build();
        }

        ChatSubCommand command = ChatSubCommandFactory.createSubCommand(args);

        if (command == null) {
            return CommandOutput.builder()
                .text("Unkown argument", "#ef4444", SegmentStyle.BOLD)
                .exitCode(CommandExitCode.FAILURE)
                .build();
        }
        
        return command.execute(args);
    }

}
