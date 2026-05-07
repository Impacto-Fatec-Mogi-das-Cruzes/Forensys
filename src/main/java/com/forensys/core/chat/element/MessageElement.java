package com.forensys.core.chat.element;

public class MessageElement extends ChatElement {
    private String text;
    private String time;
    private int participant;
    
    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }
    
    public int getParticipant() {
        return participant;
    }
}