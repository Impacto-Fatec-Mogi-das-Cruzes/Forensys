package com.forensys.ui.controller;

import com.forensys.core.chat.Contact;
import com.forensys.core.chat.ContactList;
import com.forensys.core.chat.element.ChatElement;
import com.forensys.core.chat.element.DateElement;
import com.forensys.core.chat.element.ImageElement;
import com.forensys.core.chat.element.MessageElement;
import com.forensys.service.chat.CloseContactList;
import com.forensys.service.chat.GetContactList;
import com.forensys.service.chat.GetContactListOwnerName;
import com.forensys.service.chat.GetParticipantColor;
import com.forensys.service.chat.GetParticipantName;
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
    private ContactList contactList;

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

    @FXML
    public void initialize() {
        root.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case KeyCode.ESCAPE -> {
                    messagesListView.getItems().clear();
                    selectedContactLabel.setText("");
                    contactListView.getSelectionModel().clearSelection();
                }

                case KeyCode.Q -> {CloseContactList.execute();}
                
                default -> {
                    break;
                }
            }
        });

        contactList = GetContactList.execute();

        contactListOwner.setText(GetContactListOwnerName.execute(contactList));

        contactListView.setCellFactory(param -> contactCellFactory());

        contactListView.getItems().addAll(contactList.getContacts());

        messagesListView.setCellFactory(param -> messageCellFactory());

        contactListView.getSelectionModel()
                .selectedItemProperty()
                .addListener(createContactSelectionListener());
    }

    private ListCell<Contact> contactCellFactory() {
        return new ListCell<>() {

            private final ContactComponent contactComponent = new ContactComponent();

            @Override
            protected void updateItem(Contact element, boolean empty) {
                super.updateItem(element, empty);

                if (empty || element == null) {
                    setGraphic(null);
                    return;
                }

                contactComponent.setContent(
                        element.getTitle());

                setGraphic(contactComponent);
            }
        };
    }

    private ListCell<ChatElement> messageCellFactory() {
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

                String ownerName = GetParticipantName.execute(
                        contactList,
                        selectedContact,
                        message.getParticipant());

                String color = GetParticipantColor.execute(
                        contactList,
                        selectedContact,
                        message.getParticipant());

                messageComponent.setContent(
                        ownerName,
                        color,
                        message.getText(),
                        message.getTime());

                return messageComponent;
            }

            private DateComponent updateDate(DateElement date) {

                dateComponent.setContent(
                        date.getText());

                return dateComponent;
            }

            private ImageComponent updateImage(ImageElement image) {

                Contact selectedContact = contactListView.getSelectionModel()
                        .getSelectedItem();

                String ownerName = GetParticipantName.execute(
                        contactList,
                        selectedContact,
                        image.getParticipant());

                String color = GetParticipantColor.execute(
                        contactList,
                        selectedContact,
                        image.getParticipant());

                imageComponent.setContent(
                        ownerName,
                        color,
                        image.getTime(),
                        image.getPath(),
                        image.getHeight(),
                        image.getWidth());

                return imageComponent;
            }
        };
    }

    private ChangeListener<? super Contact> createContactSelectionListener() {
        return (obs, oldContact, newContact) -> {

            if (newContact == null) {
                return;
            }

            selectedContactLabel.setText(
                    newContact.getTitle());

            messagesListView.getItems().clear();

            Contact selectedContact = contactListView.getSelectionModel()
                    .getSelectedItem();

            if (selectedContact == null) {
                return;
            }

            messagesListView.getItems().addAll(
                    selectedContact.getElements());
        };
    }
}