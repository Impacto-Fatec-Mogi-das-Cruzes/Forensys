package com.forensys.core.chat.element;

import java.util.HashMap;
import java.util.Map;

public class UnknownElement extends ChatElement{
    private Map<String, Object> other = new HashMap<>();

    public Map<String, Object> getOther() {
        return other;
    }
}
