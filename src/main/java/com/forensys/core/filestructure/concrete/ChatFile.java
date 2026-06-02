package com.forensys.core.filestructure.concrete;

import com.forensys.core.filestructure.FileSystemEntry;

public class ChatFile extends FileSystemEntry {
    private String content;

    public ChatFile() {}

    public String getContent() {
        return content;
    }
}
