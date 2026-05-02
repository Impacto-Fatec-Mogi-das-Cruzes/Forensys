package com.forensys.ui.filestructure;

import java.io.InputStream;

import com.forensys.common.loader.LoadingStrategy;

public class LoadingFileStructure implements LoadingStrategy<InputStream> {

    @Override
    public InputStream load(String sourceData) {
        InputStream is = null;

        try {
            is = getClass().getResourceAsStream("/filestructure/" + sourceData + ".json");
        } catch (Exception e) {
            System.err.println(e);
        }

        if (is == null) {
           throw new RuntimeException("File not found");
        }

        return is;
    }

}
