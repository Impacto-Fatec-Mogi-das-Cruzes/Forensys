package com.forensys.core.context;

import com.forensys.common.observer.Operation;

public enum ContextOperation {
    OPEN_FILE(new Operation("open file")),
    CLOSE_FILE(new Operation("close file")),
    CHANGE_DIR(new Operation("change dir")),
    OPEN_CONTACT(new Operation("open a contact list")),
    CLOSE_CONTACT(new Operation("close a contact list")),
    OPEN_IMAGE(new Operation("open image file")),
    CLOSE_IMAGE(new Operation("close image file"));

    private final Operation operation;
    
    ContextOperation(Operation operation) {
        this.operation = operation;
    }
    
    public Operation getOperation() {
        return operation;
    }
}
