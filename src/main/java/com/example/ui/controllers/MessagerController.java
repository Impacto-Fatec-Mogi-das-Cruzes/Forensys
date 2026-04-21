package com.example.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessagerController {
    public class Message {
        private String text;
        private boolean isUserMessage;

        public Message(String text, boolean isUserMessage) {
            this.text = text;
            this.isUserMessage = isUserMessage;
        }

        public String getText() {
            return text;
        }

        public boolean isUserMessage() {
            return isUserMessage;
        }
    }

    @FXML
    private ListView<HBox> messagesListView;
    @FXML
    private ListView<String> contactListView;
    @FXML
    private Label selectedContactLabel;

    private Map<String, List<Message>> contactMessages = new HashMap<>();

    @FXML
    public void initialize() {
        contactMessages.put("John Doe", Arrays.asList(
                new Message("Hello, how are you?", false),
                new Message("I'm good, thanks!", true),
                new Message("I'm doing well, thanks!", false),
                new Message("Great to hear!", true)
        ));
        contactMessages.put("Jane Smith", Arrays.asList(
                new Message("Hey there!", false),
                new Message("What's up?", false),
                new Message("Hey, not much!", true),
                new Message("All good!", true)
        ));
        contactMessages.put("Alice Johnson", Arrays.asList(
                new Message("Good morning!", false),
                new Message("Good morning to you too!", true),
                new Message("How's your day going?", false),
                new Message("My day is going great!", true)
        ));

        contactListView.getItems().addAll(contactMessages.keySet());

        contactListView.getSelectionModel().selectedItemProperty().addListener((obs, oldContact, newContact) -> {
            if (newContact != null) {
                selectedContactLabel.setText("Chatting with: " + newContact);

                messagesListView.getItems().clear();

                List<Message> messages = contactMessages.get(newContact);

                for (Message message : messages) {
                    HBox messageBox = createMessageBox(message.getText(), message.isUserMessage());
                    messagesListView.getItems().add(messageBox);
                }
            }
        });
    }

    private HBox createMessageBox(String message, boolean isUserMessage) {
        HBox messageBox = new HBox();
        Label messageLabel = new Label(message);
        messageLabel.setWrapText(true);

        if (isUserMessage) {
            messageBox.setStyle("-fx-alignment: CENTER_RIGHT;");
        } else {
            messageBox.setStyle("-fx-alignment: CENTER_LEFT;");
        }

        messageBox.getChildren().add(messageLabel);
        HBox.setHgrow(messageLabel, Priority.ALWAYS);
        return messageBox;
    }
}