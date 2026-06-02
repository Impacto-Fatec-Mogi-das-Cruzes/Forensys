package com.forensys.core.chat;

import java.util.List;

public class ContactList {
    private Participant owner;
    private List<Contact> contacts;

    public List<Contact> getContacts() {
        return List.copyOf(contacts);
    }
    
    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public Participant getOwner() {
        return owner;
    }
}
