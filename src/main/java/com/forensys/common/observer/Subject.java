package com.forensys.common.observer;

public interface Subject {
    
    void subscribe(Operation operation, Observer listener);

    void unsubscribe(Operation operation, Observer listener);

    void notify(Operation context);
}
