package com.example.core.filestructure.concrete;

import com.example.core.filestructure.FileSystemEntry;

public class TextFile extends FileSystemEntry {

    private String content;
    
    
    public TextFile() {}
    
    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
