package com.forensys.core.filestructure.concrete;

import com.forensys.core.filestructure.FileSystemEntry;

public class TextFile extends FileSystemEntry {

    private String content;
    
    
    public TextFile() {}
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
