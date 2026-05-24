package com.forensys.core.chat;

import com.forensys.common.HexColor;

public class Participant {
    private String name;
    private HexColor color;
    private int id;

    public String getName() {
        return name;
    }

    public String getColor() {
        return color.value();
    }
    
    public int getId() {
        return id;
    }
}
