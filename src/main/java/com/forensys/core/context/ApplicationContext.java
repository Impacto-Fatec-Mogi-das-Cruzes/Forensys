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
import com.forensys.core.filestructure.concrete.Directory;
import com.forensys.core.filestructure.concrete.TextFile;

// TODO: add observer pattern to openTextFile and make scene manager subcriber to make the following flow
/*
Controller -> "Do something" -> Service -> "Use this" -> Core -> {may update Context} -> {notify subscribers} -> Scene Manager -> "Change scene to..."
this flow will be useful for "read" command
*/
public class ApplicationContext implements Subject {

    private static ApplicationContext instance;
    private final Map<Operation, List<Observer>> eventObservers = new HashMap<>();

    private Deque<Directory> directoryPath = new ArrayDeque<>();
    private TextFile textFile;
    
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

    public void setTextFile(TextFile textFile) {
        this.textFile = textFile;
        notify(ContextOperation.OPEN_FILE.getOperation());
    }

    @Override
    public void subscribe(Operation operation, Observer listener) {
        List<Observer> observers = eventObservers.get(operation);
        observers.add(listener);
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
