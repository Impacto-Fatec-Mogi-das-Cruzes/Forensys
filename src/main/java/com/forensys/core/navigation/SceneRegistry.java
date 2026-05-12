package com.forensys.core.navigation;

import java.util.function.Supplier;

import com.forensys.common.Registry;

import javafx.scene.Scene;

public class SceneRegistry extends Registry<Supplier<Scene>> {

    private static final SceneRegistry instance = new SceneRegistry();
    
    private SceneRegistry() {
        super();
    }

    public static SceneRegistry getInstance() {
        return instance;
    }
}
