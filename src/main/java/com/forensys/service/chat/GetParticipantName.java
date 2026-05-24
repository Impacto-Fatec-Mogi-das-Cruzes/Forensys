package com.forensys.service.chat;

import com.forensys.core.chat.Contact;
import com.forensys.core.chat.ContactList;
import com.forensys.core.chat.Participant;

public class GetParticipantName {

    public static String execute(
            ContactList contactList,
            Contact selectedContact,
            int participantId
    ) {

        if (participantId == 0) {
            return contactList.getOwner()
                    .getName();
        }

        if (selectedContact != null) {

            for (Participant participant
                    : selectedContact.getParticipants()) {

                if (participant.getId() == participantId) {
                    return participant.getName();
                }
            }
        }

        return "unknown_user";
    }
}