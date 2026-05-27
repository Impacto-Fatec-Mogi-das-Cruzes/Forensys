package com.forensys.core.command.concrete.list.strategy;

import com.forensys.core.command.CommandOutputBuilder;
import com.forensys.core.filestructure.FileMetadata;
import com.forensys.core.filestructure.FileSystemEntry;

public class LongEntryRenderStrategy implements EntryRenderStrategy {

    private static final String FORMAT =
        "%-8s %-8s %-12s %-22s %-22s %-10s %-20s";

    @Override
    public void renderHeader(CommandOutputBuilder builder) {
        builder
            .styledText(
                FORMAT.formatted(
                    "hidden",
                    "blocked",
                    "size",
                    "created",
                    "updated",
                    "type",
                    "name"
                ),
                "#ffffff"
            )
            .newLine();
    }

    @Override
    public void renderEntry(CommandOutputBuilder builder, FileSystemEntry entry) {
        FileMetadata metadata = entry.getMetadata();

        builder
            .styledText(
                FORMAT.formatted(
                    metadata.hidden(),
                    metadata.blocked(),
                    metadata.size(),
                    metadata.created(),
                    metadata.updated(),
                    entry.getType(),
                    metadata.name()
                ),
                "#38bdf8"
            )
            .newLine();
    }
}