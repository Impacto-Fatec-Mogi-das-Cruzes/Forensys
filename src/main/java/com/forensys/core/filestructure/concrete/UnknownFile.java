package com.forensys.core.filestructure.concrete;

import java.util.HashMap;
import java.util.Map;

import com.forensys.core.filestructure.FileSystemEntry;

public class UnknownFile extends FileSystemEntry {
    private Map<String, Object> other = new HashMap<>();

    public Map<String, Object> getOther() {
        return other;
    }
}
