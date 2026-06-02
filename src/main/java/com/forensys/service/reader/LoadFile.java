package com.forensys.service.reader;

import com.forensys.core.navigation.FileLoader;

public class LoadFile {
    public static String execute(String path) {
        return FileLoader.getInstance().load(path);
    }
}
