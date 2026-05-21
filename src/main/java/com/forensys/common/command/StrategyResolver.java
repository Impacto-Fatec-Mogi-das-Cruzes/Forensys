package com.forensys.common.command;

import com.forensys.core.command.ParsedCommandArgs;

public interface StrategyResolver {
    ExecutionStrategy resolve(ParsedCommandArgs args);
}
