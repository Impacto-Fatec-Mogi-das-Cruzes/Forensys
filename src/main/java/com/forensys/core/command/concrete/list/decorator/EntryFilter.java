package com.forensys.core.command.concrete.list.decorator;

import java.util.List;

import com.forensys.core.filestructure.FileSystemEntry;

public interface EntryFilter {
    List<FileSystemEntry> filter(List<FileSystemEntry> entries);
}