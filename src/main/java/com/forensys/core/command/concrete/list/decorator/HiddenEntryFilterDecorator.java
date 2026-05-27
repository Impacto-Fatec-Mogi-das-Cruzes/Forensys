package com.forensys.core.command.concrete.list.decorator;

import java.util.List;

import com.forensys.core.filestructure.FileSystemEntry;

public class HiddenEntryFilterDecorator extends EntryFilterDecorator {

    public HiddenEntryFilterDecorator(EntryFilter delegate) {
        super(delegate);
    }

    @Override
    public List<FileSystemEntry> filter(List<FileSystemEntry> entries) {
        return delegate.filter(entries)
            .stream()
            .filter(entry -> !entry.getMetadata().hidden())
            .toList();
    }
}