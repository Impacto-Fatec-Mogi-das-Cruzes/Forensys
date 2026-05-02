package com.forensys.ui.filestructure;

import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forensys.common.parser.ParsingStrategy;
import com.forensys.core.filestructure.FileSystemEntry;

// File Structure Parsing Strategy
public class ParsingFileStrucure implements ParsingStrategy<FileSystemEntry> {
    @Override
    public FileSystemEntry parse(String resource) {

        ObjectMapper mapper = new ObjectMapper();
        FileSystemEntry data = null;
        try {
            InputStream is = FileStructureLoader.getInstance().load(resource);
            data = mapper.readValue(is, FileSystemEntry.class);
        } catch (Exception e) {
            System.err.println(e);
        }

        return data;
    }
}
