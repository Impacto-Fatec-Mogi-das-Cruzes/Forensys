package com.forensys.core.command.concrete.chat;

import com.forensys.core.command.ParsedCommandArgs;

public class ChatSubCommandFactory {
    public static ChatSubCommand createSubCommand(ParsedCommandArgs args) {

        // TODO: add subcommand register that parses a json to a Contact and adds it to the ContactList in the ApplicationContext
        switch (args.positionals().getFirst()) {
            case "open":
                return new OpenSubCommand();
            default:
                break;
        }
        return null;

    }
}
