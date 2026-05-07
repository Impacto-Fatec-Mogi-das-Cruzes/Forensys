package com.forensys.core.chat.element;

public class ImageElement extends ChatElement {
    private String path;
    private String time;
    private int width;
    private int height;
    private int participant;


    public String getPath() {
        return path;
    }
    
    public String getTime() {
        return time;
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public int getParticipant() {
        return participant;
    }

}
