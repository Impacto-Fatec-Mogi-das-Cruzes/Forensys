package com.forensys.core.setting;

import java.io.InputStream;

import com.forensys.common.loader.Loader;

public class SettingsLoader extends Loader<InputStream>{

    private static SettingsLoader instance;

    private SettingsLoader() {
        super(new LoadingSettings());
    }

    public static SettingsLoader getInstance() {
        if (instance == null) {
            instance = new SettingsLoader();
        }
        return instance;
    }

}
