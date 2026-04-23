package com.example.core.filestructure;

import com.example.core.filestructure.concrete.Directory;
import com.example.core.filestructure.concrete.TextFile;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Directory.class, name = "directory"),
        @JsonSubTypes.Type(value = TextFile.class, name = "text")
})
public abstract class FileSystemEntry {

    private String type;
    private FileMetadata metadata;

    public FileMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(FileMetadata metadata) {
        this.metadata = metadata;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
