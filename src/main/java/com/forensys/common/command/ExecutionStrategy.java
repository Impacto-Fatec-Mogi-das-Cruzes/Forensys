package com.forensys.common.command;

import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.ParsedCommandArgs;

public interface ExecutionStrategy {
    CommandOutput execute(ParsedCommandArgs arguments);
}
