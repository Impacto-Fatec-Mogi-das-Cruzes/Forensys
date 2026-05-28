package com.forensys.core.command.concrete.list.strategy;

import com.forensys.core.command.CommandOutputBuilder;
import com.forensys.core.filestructure.FileSystemEntry;

public class SimpleEntryRenderStrategy implements EntryRenderStrategy {

    @Override
    public void renderHeader(CommandOutputBuilder builder) {
        String header = "%-20s %-10s".formatted("TYPE", "NAME");

        builder
            .styledText(header, "#ffffff")
            .newLine()
            .styledText("-".repeat(header.length()), "#ffffff")
            .newLine();
    }

    @Override
    public void renderEntry(CommandOutputBuilder builder, FileSystemEntry entry) {
        builder
            .styledText(
                "%-20s %-10s".formatted(
                    entry.getType(),
                    entry.getMetadata().name()
                ),
                "#38bdf8"
            )
            .newLine();
    }
}