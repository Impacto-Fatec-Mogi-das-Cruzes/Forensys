package com.forensys.common.observer;

public interface Subject {
    
    void subscribe(Observer listener, Operation... operation);

    void unsubscribe(Operation operation, Observer listener);

    void notify(Operation context);
}
