package com.forensys.core.filestructure;

import com.forensys.common.parser.Parser;

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

}
