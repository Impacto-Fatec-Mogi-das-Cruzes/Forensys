package com.forensys.core.navigation;

import com.forensys.common.loader.Loader;

public class FileLoader extends Loader<String> {

    private static FileLoader instance;

    private FileLoader() {
        super(new LoadingFile());
    }

    public static FileLoader getInstance() {
        if (instance == null) {
            instance = new FileLoader();
        }
        return instance;
    }
}
