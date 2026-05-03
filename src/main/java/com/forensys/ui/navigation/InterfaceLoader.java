package com.forensys.ui.navigation;

import com.forensys.common.loader.Loader;

import javafx.scene.Parent;

public class InterfaceLoader extends Loader<Parent>{
    
    private static InterfaceLoader instance;

    private InterfaceLoader() {
        super(new LoadingInterface());
    }

    public static InterfaceLoader getInstance() {
        if (instance == null) {
            instance = new InterfaceLoader();
        }
        return instance;
    }
}
