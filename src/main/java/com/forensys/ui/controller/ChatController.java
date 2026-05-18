package com.forensys.ui.controller;

import com.forensys.core.chat.Contact;
import com.forensys.core.chat.ContactList;
import com.forensys.core.chat.Participant;
import com.forensys.core.chat.element.ChatElement;
import com.forensys.core.chat.element.DateElement;
import com.forensys.core.chat.element.ImageElement;
import com.forensys.core.chat.element.MessageElement;
import com.forensys.core.context.ApplicationContext;
import com.forensys.ui.components.ContactComponent;
import com.forensys.ui.components.DateComponent;
import com.forensys.ui.components.ImageComponent;
import com.forensys.ui.components.MessageComponent;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;

public class ChatController {

    @FXML
    private Label contactListOwner;

    @FXML
    private Label selectedContactLabel;

    @FXML
    private BorderPane root;

    @FXML
    private ListView<ChatElement> messagesListView;

    @FXML
    private ListView<Contact> contactListView;

    //TODO: make it into a service
    @FXML
    public void initialize() {
        root.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case KeyCode.ESCAPE -> {
                    messagesListView.getItems().clear();
                    selectedContactLabel.setText("");
                    contactListView.getSelectionModel().clearSelection();
                }

                case KeyCode.Q -> {
                    ApplicationContext.getInstance().closeContactList();
                }

                default -> {
                    break;
                }
            }
        });

        ContactList contactList = ApplicationContext.getInstance().geContactList();
        contactListOwner.setText(contactList.getOwner().getName());

        contactListView.setCellFactory(param -> contactCellFactoty());

        contactListView.getItems().addAll(contactList.getContacts());

        messagesListView.setCellFactory(param -> messageCellFactory(contactList));

        contactListView.getSelectionModel()
                .selectedItemProperty()
                .addListener(createContactSelectionListener());
    }

    private ListCell<Contact> contactCellFactoty() {
        return new ListCell<>() {

            private final ContactComponent contactComponent = new ContactComponent();

            @Override
            protected void updateItem(Contact element, boolean empty) {
                super.updateItem(element, empty);

                if (empty || element == null) {
                    setGraphic(null);
                } else {
                    contactComponent.setContent(element.getTitle());
                    setGraphic(contactComponent);
                }
            }
        };
    }

    private ListCell<ChatElement> messageCellFactory(ContactList contactList) {
        return new ListCell<>() {

            private final MessageComponent messageComponent = new MessageComponent();
            private final DateComponent dateComponent = new DateComponent();
            private final ImageComponent imageComponent = new ImageComponent();

            @Override
            protected void updateItem(ChatElement element, boolean empty) {
                super.updateItem(element, empty);

                if (empty || element == null) {
                    setGraphic(null);
                    return;
                }

                switch (element) {

                    case MessageElement message -> setGraphic(updateMessage(message));

                    case DateElement date -> setGraphic(updateDate(date));

                    case ImageElement image -> setGraphic(updateImage(image));

                    default -> setGraphic(new Label("Message not loaded..."));
                }
            }

            private MessageComponent updateMessage(MessageElement message) {
                Contact selectedContact = contactListView.getSelectionModel().getSelectedItem();

                String ownerName = "unknown_user";
                String color = "#999999";

                if (message.getParticipant() == 0) {
                    ownerName = contactList.getOwner().getName();
                    color = contactList.getOwner().getColor();
                } else if (selectedContact != null) {
                    for (Participant participant : selectedContact.getParticipants()) {
                        if (participant.getId() == message.getParticipant()) {
                            ownerName = participant.getName();
                            color = participant.getColor();
                            break;
                        }
                    }
                }
                messageComponent.setContent(ownerName, color, message.getText(), message.getTime());
                return messageComponent;
            }

            private DateComponent updateDate(DateElement date) {
                dateComponent.setContent(date.getText());
                return dateComponent;
            }

            private ImageComponent updateImage(ImageElement image) {
                Contact selectedContact = contactListView.getSelectionModel().getSelectedItem();

                String ownerName = "unknown_user:";
                String color = "#999999";

                if (image.getParticipant() == 0) {
                    ownerName = contactList.getOwner().getName();
                    color = contactList.getOwner().getColor();
                } else if (selectedContact != null) {
                    for (Participant participant : selectedContact.getParticipants()) {
                        if (participant.getId() == image.getParticipant()) {
                            ownerName = participant.getName();
                            color = participant.getColor();
                            break;
                        }
                    }
                }

                imageComponent.setContent(ownerName, color, image.getTime(), image.getPath(), image.getHeight(), image.getWidth());
                return imageComponent;
            }
        };
    }

    private ChangeListener<? super Contact> createContactSelectionListener() {
        return (obs, oldContact, newContact) -> {

            if (newContact == null) {
                return;
            }

            selectedContactLabel.setText(newContact.getTitle());

            messagesListView.getItems().clear();

            Contact selectedContact = contactListView.getSelectionModel().getSelectedItem();

            if (selectedContact == null) {
                return;
            }

            messagesListView.getItems().addAll(
                    selectedContact.getElements());
        };
    }
}