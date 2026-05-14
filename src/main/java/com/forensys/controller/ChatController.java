package com.forensys.controller;

import java.io.InputStream;

import com.forensys.core.chat.Contact;
import com.forensys.core.chat.ContactList;
import com.forensys.core.chat.Participant;
import com.forensys.core.chat.element.ChatElement;
import com.forensys.core.chat.element.DateElement;
import com.forensys.core.chat.element.ImageElement;
import com.forensys.core.chat.element.MessageElement;
import com.forensys.core.context.ApplicationContext;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ChatController {
    @FXML
    private BorderPane root;

    @FXML
    private ListView<ChatElement> messagesListView;
    @FXML
    private ListView<Contact> contactListView; //TODO make it ListView<Contact> and add a cell factory to it
    @FXML
    private Label selectedContactLabel;

    @FXML
    public void initialize() {
        root.setOnKeyReleased(event -> {

            if (event.getCode() == KeyCode.ESCAPE) {
                messagesListView.getItems().clear();
                selectedContactLabel.setText("[ CHAT ]");
                contactListView.getSelectionModel().clearSelection();
            }

            if (event.getCode() == KeyCode.Q) {
                ApplicationContext.getInstance().closeContactList();
            }
        });

        demo2_init_process();
    }

    /**
     * Basic Scheme of this method
     * get the contact list and add it to the contactListView
     * defines the cell factory for messageListView, basically, its saying: "when
     * something updates in here use this process to generate the item in the
     * ListView"
     * 
     * a listener is added to contactListView, so when one contact is selected it
     * finds the list of elements associated and uses addAll to add it, which causes
     * an update in messageListView, then causing it to use the cell factory to put
     * the contents of the element in the correct structure
     * 
     * TODO: make into a service process
     */
    private void demo2_init_process() {

        ContactList contactList = ApplicationContext.getInstance().geContactList();

        contactListView.setCellFactory(param -> new ListCell<>() {

            private final HBox elementBox = new HBox();
            private final Label titleLabel = new Label();

            {
                elementBox.getChildren().add(titleLabel);
            }

            @Override
            protected void updateItem(Contact element, boolean empty) {
                super.updateItem(element, empty);

                if (empty || element == null) {
                    setGraphic(null);
                } else {
                    System.out.println(titleLabel.getText());
                    titleLabel.setText(element.getTitle());
                    setGraphic(elementBox);
                }
                super.updateItem(element, empty);
            }
        });

        contactListView.getItems().addAll(contactList.getContacts());

        messagesListView.setCellFactory(param -> new ListCell<>() {

            @Override
            protected void updateItem(ChatElement element, boolean empty) {

                super.updateItem(element, empty);

                if (empty || element == null) {
                    setGraphic(null);
                    return;
                }

                VBox elementBox = new VBox();

                Contact selectedContact = contactListView.getSelectionModel().getSelectedItem();

                switch (element) {

                    case MessageElement message -> {

                        String color = "#999999";
                        Label owner = new Label("unknown_user ");

                        if (selectedContact != null) {

                            for (Participant participant : selectedContact.getParticipants()) {

                                if (participant.getId() == message.getParticipant()) {

                                    owner = new Label(
                                            participant.getName() + " ");

                                    color = participant.getColor();
                                    break;
                                }
                            }
                        }

                        owner.setStyle(
                                "-fx-text-fill: " + color + ";" +
                                        "-fx-font-weight: bold;");

                        Label text = new Label(message.getText());

                        text.setWrapText(true);

                        text.maxWidthProperty().bind(
                                messagesListView.widthProperty().subtract(60));

                        Label time = new Label(message.getTime());

                        time.setStyle(
                                "-fx-text-fill: #999999;" +
                                        "-fx-font-size: " +
                                        (owner.getFont().getSize() * 0.8) +
                                        "px;");

                        HBox messageBox = new HBox(owner, time);

                        messageBox.setStyle(
                                "-fx-alignment: BOTTOM_LEFT;");

                        elementBox.getChildren().addAll(
                                messageBox,
                                text);
                    }

                    case DateElement date -> {

                        Label text = new Label(date.getText());

                        text.setStyle(
                                "-fx-text-fill: #999999;");

                        elementBox.setStyle(
                                "-fx-alignment: center;");

                        elementBox.getChildren().add(text);
                    }

                    case ImageElement image -> {

                        String color = "#999999";
                        Label owner = new Label("unknown_user:");

                        if (selectedContact != null) {

                            for (Participant participant : selectedContact.getParticipants()) {

                                if (participant.getId() == image.getParticipant()) {

                                    color = participant.getColor();

                                    owner = new Label(
                                            participant.getName() + ":");

                                    break;
                                }
                            }
                        }

                        owner.setStyle(
                                "-fx-text-fill: " + color);

                        ImageView imageView = new ImageView();

                        InputStream stream = getClass().getResourceAsStream(
                                image.getPath());

                        if (stream != null) {

                            imageView.setImage(new Image(stream));

                            imageView.setFitHeight(image.getHeight());
                            imageView.setFitWidth(image.getWidth());
                        }

                        Label time = new Label(image.getTime());

                        time.setStyle(
                                "-fx-text-fill: #999999;" +
                                        "-fx-font-size: " +
                                        (owner.getFont().getSize() * 0.8) +
                                        "px;");

                        elementBox.getChildren().addAll(
                                owner,
                                imageView,
                                time);
                    }

                    default -> {

                        elementBox.getChildren().add(
                                new Label("Message not loaded..."));
                    }
                }

                setGraphic(elementBox);
            }
        });

        contactListView.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldContact, newContact) -> {

                    if (newContact == null) {
                        return;
                    }

                    selectedContactLabel.setText(
                            "[ CHAT: " + newContact.getTitle() + " ]");

                    messagesListView.getItems().clear();

                    Contact selectedContact = contactListView.getSelectionModel().getSelectedItem();

                    if (selectedContact == null) {
                        return;
                    }

                    messagesListView.getItems().addAll(
                            selectedContact.getElements());
                });
    }
}