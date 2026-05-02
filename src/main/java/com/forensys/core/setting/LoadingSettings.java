package com.forensys.core.setting;

import java.io.InputStream;

import com.forensys.common.loader.LoadingStrategy;

public class LoadingSettings implements LoadingStrategy<InputStream> {

    @Override
    public InputStream load(String sourceData) {
        InputStream is = null;

        try {
            is = getClass().getResourceAsStream("/config/" + sourceData + ".json");
        } catch (Exception e) {
            System.err.println(e);
        }

        if (is == null) {
           throw new RuntimeException("File not found");
        }

        return is;
    }

}
