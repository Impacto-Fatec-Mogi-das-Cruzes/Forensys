package com.forensys.ui.navigation;

import com.forensys.common.parser.ParsingStrategy;
import javafx.scene.Scene;

public class ParsingInterface implements ParsingStrategy<Scene> {

    @Override
    public Scene parse(String sourceData) {
        return new Scene(
                InterfaceLoader.getInstance().load(sourceData)
            );
    }

}
