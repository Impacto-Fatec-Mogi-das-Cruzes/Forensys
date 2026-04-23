package com.example.core.filestructure;

import java.util.Map;

import com.example.core.filestructure.concrete.Directory;

public class DirectorySnapshot {
    
    private final Directory directory;
    private final String type;
    private final FileMetadata metadata;
    private final Map<String, FileSystemEntry> children;
    
    public DirectorySnapshot(Directory directory, String type, FileMetadata metadata, Map<String, FileSystemEntry> children) {
        this.directory = directory;
        this.type = type;
        this.metadata = metadata;
        this.children = children;
    }

    public String getType() {
        return type;
    }

    public FileMetadata getMetadata() {
        return metadata;
    }

    public Map<String, FileSystemEntry> getChildren() {
        return children;
    }

    public void restore() {
        this.directory.setType(this.type);
        this.directory.setMetadata(this.metadata);
        this.directory.setChildren(this.children);
    }
    
}
