package com.forensys.core.context;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.forensys.common.observer.Observer;
import com.forensys.common.observer.Operation;
import com.forensys.common.observer.Subject;
import com.forensys.core.chat.ContactList;
import com.forensys.core.filestructure.concrete.Directory;
import com.forensys.core.filestructure.concrete.TextFile;

public class ApplicationContext implements Subject {

    private static ApplicationContext instance;
    private final Map<Operation, List<Observer>> eventObservers = new HashMap<>();

    private Deque<Directory> directoryPath = new ArrayDeque<>();
    private TextFile textFile;
    private ContactList contactList;
    
    private ApplicationContext(Directory startDirectory) {
        directoryPath.push(startDirectory);

        for (ContextOperation operation : ContextOperation.values()) {
            eventObservers.put(operation.getOperation(), new ArrayList<>());
        }
    }

    public static void init(Directory startDirectory) {
        if (instance != null) {
            throw new IllegalStateException("ApplicationContext has already being initialized");
        }
        instance = new ApplicationContext(startDirectory);
    }

    public static ApplicationContext getInstance() {
        if (instance == null) {
            throw new IllegalStateException("ApplicationContext hasn't being initialized");
        }
        return instance;
    }

    public Directory getCurrentDirectory() {
        return directoryPath.peek();
    }

    public void setCurrentDirectory(Directory newDirectory) {
        directoryPath.push(newDirectory);
    }

    public void restoreDirectory() {
        if (directoryPath.size() <= 1) {
            throw new NoSuchElementException("Cannot remove element");
        }
        directoryPath.pop();
    }

    public TextFile getTextFile() {
        return textFile;
    }

    public void openFile(TextFile textFile) {
        if (textFile == null) {
            throw new IllegalStateException("Text file cannot be null when opening a file");
        }
        this.textFile = textFile;
        notify(ContextOperation.OPEN_FILE.getOperation());
    }

    public void closeFile() {
        if (this.textFile == null) {
            throw new IllegalStateException("File is null, cannot be closed");
        }
        this.textFile = null;
        notify(ContextOperation.CLOSE_FILE.getOperation());
    }

    public ContactList geContactList() {
        return this.contactList;
    }

    public void openContactList(ContactList contactList) {
        if (contactList == null) {
            throw new IllegalStateException("Contact list cannot be null when opening a contact list");
        }
        this.contactList = contactList;
        notify(ContextOperation.OPEN_CONTACT.getOperation());
    }

    public void closeContactList() {
        if (this.contactList == null) {
            throw new IllegalStateException("Contact list is null, cannot be closed");
        }
        this.contactList = null;
        notify(ContextOperation.CLOSE_CONTACT.getOperation());
    }

    @Override
    public void subscribe(Observer listener, Operation... operations) {
        for (Operation operation : operations) {
            List<Observer> observers = eventObservers.get(operation);
            observers.add(listener);
        }
    }

    @Override
    public void unsubscribe(Operation operation, Observer listener) {
        List<Observer> observers = eventObservers.get(operation);
        observers.remove(listener);
    }

    @Override
    public void notify(Operation operation) {
        List<Observer> observers = eventObservers.get(operation);
        for (Observer observer : observers) {
            observer.update(operation);
        }
    }
}
