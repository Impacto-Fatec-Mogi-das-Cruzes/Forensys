package com.forensys.core.command.concrete.list.decorator;

import java.util.List;

import com.forensys.core.filestructure.FileSystemEntry;

public class BaseEntryFilter implements EntryFilter {

    @Override
    public List<FileSystemEntry> filter(List<FileSystemEntry> entries) {
        return entries;
    }
}