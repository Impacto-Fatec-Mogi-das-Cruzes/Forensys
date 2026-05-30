package com.forensys.core.command.concrete.chat;

import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.ParsedCommandArgs;

public interface ChatSubCommand {
    CommandOutput execute(ParsedCommandArgs args);
}
