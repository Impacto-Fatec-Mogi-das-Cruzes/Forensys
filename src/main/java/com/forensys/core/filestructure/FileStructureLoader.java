package com.forensys.core.filestructure;

import java.io.InputStream;

import com.forensys.common.loader.Loader;

public class FileStructureLoader extends Loader<InputStream>{

    private static FileStructureLoader instance;

    private FileStructureLoader() {
        super(new LoadingFileStructure());
    }

    public static FileStructureLoader getInstance() {
        if (instance == null) {
            instance = new FileStructureLoader();
        }
        return instance;
    }

}
