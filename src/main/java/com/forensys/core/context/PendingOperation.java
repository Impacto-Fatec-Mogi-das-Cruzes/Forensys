package com.forensys.core.context;

import java.util.function.Function;

import com.forensys.core.command.CommandOutput;

public record PendingOperation(
    Function<String, CommandOutput> callback
) {}