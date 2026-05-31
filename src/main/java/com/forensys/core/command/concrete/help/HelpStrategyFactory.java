package com.forensys.core.command.concrete.help;

import com.forensys.core.command.ParsedCommandArgs;

public class HelpStrategyFactory {
    
    public static MessageStrategy createMessageBuilderStrategy(ParsedCommandArgs args) {
        if (args.positionals().isEmpty()) {
            return new GeneralMessageStrategy();
        }
        return new SpecificMessageStrategy(args.positionals().getFirst());
    }
}
