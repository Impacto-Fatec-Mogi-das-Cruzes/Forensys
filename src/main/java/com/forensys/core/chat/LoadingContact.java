package com.forensys.core.chat;

import java.io.InputStream;

import com.forensys.common.loader.LoadingStrategy;

public class LoadingContact implements LoadingStrategy<InputStream> {

    @Override
    public InputStream load(String sourceData) {
        InputStream is = null;

        try {
            is = getClass().getResourceAsStream("/assets/filestructure/" + sourceData);
        } catch (Exception e) {
            System.err.println(e);
        }

        if (is == null) {
           throw new RuntimeException("File not found");
        }

        return is;
    }

}
