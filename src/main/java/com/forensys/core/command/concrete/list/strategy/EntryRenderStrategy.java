package com.forensys.core.command.concrete.list.strategy;

import com.forensys.core.command.CommandOutputBuilder;
import com.forensys.core.filestructure.FileSystemEntry;

public interface EntryRenderStrategy {
    void renderHeader(CommandOutputBuilder builder);
    void renderEntry(CommandOutputBuilder builder, FileSystemEntry entry);
}