package com.forensys.core.chat;

import java.util.List;

import com.forensys.core.chat.element.ChatElement;

public class Contact {
    private String title;
    private List<Participant> participants;
    private List<ChatElement> elements;

    public String getTitle() {
        return title;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public List<ChatElement> getElements() {
        return elements;
    }
}
