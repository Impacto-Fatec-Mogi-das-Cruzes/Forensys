package com.example.core.filestructure.concrete;

import java.util.Map;

import com.example.core.filestructure.DirectorySnapshot;
import com.example.core.filestructure.FileSystemEntry;

public class Directory extends FileSystemEntry {
    
    private Map<String, FileSystemEntry> children;

    public Directory() {}

    public Map<String, FileSystemEntry> getChildren() {
        return children;
    }

    public void setChildren(Map<String, FileSystemEntry> children) {
        this.children = children;
    }
    
    public DirectorySnapshot save() {
        return new DirectorySnapshot(this, this.getType(), this.getMetadata(), this.getChildren());
    }

}
