package com.forensys.core.chat.element;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME, 
    include = JsonTypeInfo.As.PROPERTY, 
    property = "type",
    visible = true,
    defaultImpl = UnknownElement.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MessageElement.class, name = "message"),
        @JsonSubTypes.Type(value = DateElement.class, name = "date"),
        @JsonSubTypes.Type(value = ImageElement.class, name = "image"),
})
public abstract class ChatElement { 
    private String type;

    public String getType() {
        return type;
    }
}
