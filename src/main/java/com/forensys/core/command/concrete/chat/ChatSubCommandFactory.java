package com.forensys.core.command.concrete.chat;

import com.forensys.core.command.ParsedCommandArgs;

public class ChatSubCommandFactory {
    public static ChatSubCommand createSubCommand(ParsedCommandArgs args) {
        
        switch (args.positionals().getFirst()) {
            case "open":
                return new OpenSubCommand();
            case "register":
                return new RegisterSubCommand();
            default:
                break;
        }
        return null;

    }
}
