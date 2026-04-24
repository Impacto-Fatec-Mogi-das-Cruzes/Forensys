package com.forensys.core;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

import com.forensys.core.filestructure.concrete.Directory;

// TODO: add observer pattern to openTextFile and make scene manager subcriber to make the following flow
/*
Controller -> "Do something" -> Service -> "Use this" -> Core -> {may update Context} -> {notify subscribers} -> Scene Manager -> "Change scene to..."
this flow will be useful for "read" command
*/
public class ApplicationContext {

    private static ApplicationContext instance;
    private Directory currentDirectory;
    // private TextFile openTextFile;
    private Deque<Directory> history = new ArrayDeque<>();

    private ApplicationContext() {
    }

    public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }
        return instance;
    }

    public Directory getCurrentDirectory() {
        return this.currentDirectory;
    }

    public void setCurrentDirectory(Directory newDirectory) {
        if (currentDirectory != null) {
            history.add(currentDirectory);
        }
        this.currentDirectory = newDirectory;
    }

    public void restore() {
        if (history.isEmpty()) {
            throw new NoSuchElementException("Deque is empty no item to be removed");
        }
        currentDirectory = history.pop();
    }
}
