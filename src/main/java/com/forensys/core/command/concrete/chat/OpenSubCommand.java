package com.forensys.core.command.concrete.chat;

import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.ParsedCommandArgs;
import com.forensys.core.command.SegmentStyle;
import com.forensys.core.context.ApplicationContext;

public class OpenSubCommand implements ChatSubCommand {

    @Override
    public CommandOutput execute(ParsedCommandArgs args) {

        if (args.positionals().size() > 1) {
            return CommandOutput.builder()
                .styledText("Too many arguments passed for chat command open", "#ef4444", SegmentStyle.BOLD)
                .exitCode(CommandExitCode.FAILURE)
                .build();
        }
        
        ApplicationContext.getInstance().openContactList();
        return CommandOutput.builder()
            .styledText("Opening contact list...", "#38bdf8", SegmentStyle.BOLD)
            .exitCode(CommandExitCode.SUCCESS)
            .build();
    }

}
