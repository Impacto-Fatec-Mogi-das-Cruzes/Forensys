package com.forensys.core.command.concrete.chat;

import com.forensys.core.command.ParsedCommandArgs;

public class ChatSubCommandFactory {
    public static ChatSubCommand createSubCommand(ParsedCommandArgs args) {

        switch (args.positionals().getFirst()) {
            case "list": 
                return new ListSubCommand();
            case "open":
                return new OpenSubCommand();
            default:
                break;
        }
        return null;

    }
}
