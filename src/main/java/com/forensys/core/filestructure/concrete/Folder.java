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

    public List<Folder> getDirectories() {
        List<Folder> directories = new ArrayList<>();

        for (FileSystemEntry child : children) {
            if (child instanceof Folder directory) {
                directories.add(directory);
            }
        }

        return directories;   
    }
}
