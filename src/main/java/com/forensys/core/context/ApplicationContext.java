package com.forensys.core.context;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import com.forensys.common.observer.Observer;
import com.forensys.common.observer.Operation;
import com.forensys.common.observer.Subject;
import com.forensys.core.chat.ContactList;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.filestructure.concrete.Folder;
import com.forensys.core.filestructure.concrete.ImageFile;
import com.forensys.core.filestructure.concrete.TextFile;

public class ApplicationContext implements Subject {

    private static ApplicationContext instance;
    private final Map<Operation, List<Observer>> eventObservers = new HashMap<>();

    private Folder root;
    private Deque<Folder> directoryPath = new ArrayDeque<>();
    private TextFile textFile;
    private ContactList contactList;
    private ImageFile imageFile;

    private PendingExecution pendingExecution;
    private ExecutionContext executionContext;
    private PendingOperation pendingOperation;

    private ApplicationContext(Folder startDirectory, ContactList initialContactList) {
        directoryPath.push(startDirectory);
        root = startDirectory;

        contactList = initialContactList;

        for (ContextOperation operation : ContextOperation.values()) {
            eventObservers.put(operation.getOperation(), new ArrayList<>());
        }
    }

    public static void init(Folder startDirectory, ContactList initialContactList) {
        if (instance != null) {
            throw new IllegalStateException("ApplicationContext has already being initialized");
        }
        instance = new ApplicationContext(startDirectory, initialContactList);
    }

    public static ApplicationContext getInstance() {
        if (instance == null) {
            throw new IllegalStateException("ApplicationContext hasn't being initialized");
        }
        return instance;
    }

    public Folder getCurrentDirectory() {
        return directoryPath.peek();
    }

    public void setCurrentDirectory(Folder newDirectory) {
        directoryPath.push(newDirectory);
    }

    public void restoreDirectory() {
        if (directoryPath.size() <= 1) {
            throw new IllegalStateException("No parent directory to go back to");
        }
        directoryPath.pop();
    }

    public void returnRootDirectory() {
        directoryPath.clear();
        directoryPath.push(root);
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

    public ContactList getContactList() {
        return this.contactList;
    }

    public void openContactList() {
        if (contactList == null) {
            throw new IllegalStateException("Contact list cannot be null when opening a contact list");
        }
        notify(ContextOperation.OPEN_CONTACT.getOperation());
    }

    public void closeContactList() {
        notify(ContextOperation.CLOSE_CONTACT.getOperation());
    }

    public ImageFile getImageFile() {
        return imageFile;
    }

    public void openImage(ImageFile imageFile) {
        if (imageFile == null) {
            throw new IllegalStateException("Contact list cannot be null when opening a contact list");
        }
        this.imageFile = imageFile;
        notify(ContextOperation.OPEN_IMAGE.getOperation());
    }

    public void closeImage() {
        if (this.imageFile == null) {
            throw new IllegalStateException("Image file is null, cannot be closed");
        }
        this.imageFile = null;
        notify(ContextOperation.CLOSE_IMAGE.getOperation());
    }

    public void clearAllExecution() {
        this.pendingExecution = null;
        this.executionContext = null;
        this.pendingOperation = null;
    }

    public void setPendingExecution(Supplier<CommandOutput> pendingExecution) {
        this.pendingExecution = new PendingExecution(pendingExecution);
    }

    public PendingExecution getPendingExecution() {
        return this.pendingExecution;
    }

    public void clearPendingExecution() {
        this.pendingExecution = null;
    }

    public ExecutionContext getExecutionContext() {
        return executionContext;
    }

    public void setExecutionContext(ExecutionContext executionContext) {
        this.executionContext = executionContext;
    }

    public void clearExecutionContext() {
        this.executionContext = null;
    }

    public PendingOperation getPendingOperation() {
        return pendingOperation;
    }
    
    public void setPendingOperation(PendingOperation pendingOperation) {
        this.pendingOperation = pendingOperation;
    }

    public void clearPendingOperation() {
        this.pendingOperation = null;
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
