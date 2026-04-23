package com.example.ui;

import java.io.InputStream;

import com.example.common.parser.ParsingStrategy;
import com.example.core.filestructure.FileSystemEntry;
import com.fasterxml.jackson.databind.ObjectMapper;

// File Structure Parsing Strategy
public class ParsingFileStrucure implements ParsingStrategy<FileSystemEntry> {
    @Override
    public FileSystemEntry parse(String resource) {
        FileSystemEntry data = null;

        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream is = getClass().getResourceAsStream("/filestructure/" + resource);
            if (is == null) {
               throw new RuntimeException("File not found");
            }
            data = mapper.readValue(is, FileSystemEntry.class);
        } catch (Exception e) {
            System.err.println(e);
        }
        return data;
    }
}
