package com.forensys.core.context;

import java.util.function.Supplier;

import com.forensys.core.command.CommandOutput;

public record PendingExecution(
    Supplier<CommandOutput> callback
) {}
