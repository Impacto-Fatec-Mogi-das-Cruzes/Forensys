package com.forensys.service.chat;

import com.forensys.common.HexColor;
import com.forensys.core.chat.Contact;
import com.forensys.core.chat.ContactList;
import com.forensys.core.chat.Participant;

public class GetParticipantColor {

    public static String execute(
            ContactList contactList,
            Contact selectedContact,
            int participantId
    ) {

        if (participantId == 0) {
            return contactList.getOwner()
                    .getColor();
        }

        if (selectedContact != null) {

            for (Participant participant
                    : selectedContact.getParticipants()) {

                if (participant.getId() == participantId) {
                    return participant.getColor();
                }
            }
        }

        return HexColor.of("#999999")
                .value();
    }
}