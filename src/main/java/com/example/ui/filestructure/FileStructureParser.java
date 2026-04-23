package com.example.ui.filestructure;

import com.example.common.parser.Parser;
import com.example.core.filestructure.FileSystemEntry;

public class FileStructureParser extends Parser<FileSystemEntry> {

    private static FileStructureParser instance;

    private FileStructureParser() {
        super(new ParsingFileStrucure());
    }
    
    public static FileStructureParser getInstance() {
        if (instance == null) {
            instance = new FileStructureParser();
        }
        return instance;
    }

    @Override
    public FileSystemEntry parse(String resource) {
        return getParseStrategy().parse(resource);
    }
}
