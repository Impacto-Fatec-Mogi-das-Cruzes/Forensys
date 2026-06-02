package com.forensys.core.filestructure.concrete;

import java.util.ArrayList;
import java.util.List;

import com.forensys.core.filestructure.FileSystemEntry;

public class Folder extends FileSystemEntry {
    
    private List<FileSystemEntry> children;

    public Folder() {}

    public List<FileSystemEntry> getChildren() {
        return new ArrayList<>(children);
    }

    public <T extends FileSystemEntry> List<T> getChildrenOfType(Class<T> type) {
        return children.stream()
            .filter(type::isInstance)
            .map(type::cast)
            .toList();
    }
}
