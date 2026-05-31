package com.forensys.core.filestructure.concrete;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.forensys.core.filestructure.FileSystemEntry;

public class UnknownFile extends FileSystemEntry {

    private final Map<String, Object> other = new HashMap<>();

    @JsonAnySetter
    public void add(String key, Object value) {
        other.put(key, value);
    }

    public Map<String, Object> getOther() {
        return other;
    }
}
