package com.forensys.ui.navigation;

import com.forensys.common.parser.Parser;

import javafx.scene.Scene;

public class InterfaceParser extends Parser<Scene>{

    private static InterfaceParser instance;

    private InterfaceParser() {
        super(new ParsingInterface());
    }
    
    public static InterfaceParser getInstance() {
        if (instance == null) {
            instance = new InterfaceParser();
        }
        return instance;
    }


}
