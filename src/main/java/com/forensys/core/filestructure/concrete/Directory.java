package com.forensys.core.filestructure.concrete;

import java.util.ArrayList;
import java.util.List;

import com.forensys.core.filestructure.FileSystemEntry;

public class Directory extends FileSystemEntry {
    
    private List<FileSystemEntry> children;

    public Directory() {}

    public List<FileSystemEntry> getChildren() {
        return new ArrayList<>(children);
    }

    public List<Directory> getDirectories() {
        List<Directory> directories = new ArrayList<>();

        for (FileSystemEntry child : children) {
            if (child instanceof Directory directory) {
                directories.add(directory);
            }
        }

        return directories;   
    }
}
