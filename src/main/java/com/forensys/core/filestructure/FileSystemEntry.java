package com.forensys.core.filestructure;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.forensys.core.filestructure.concrete.Directory;
import com.forensys.core.filestructure.concrete.ImageFile;
import com.forensys.core.filestructure.concrete.TextFile;
import com.forensys.core.filestructure.concrete.UnknownFile;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME, 
    include = JsonTypeInfo.As.PROPERTY, 
    property = "type",
    visible = true,
    defaultImpl = UnknownFile.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Directory.class, name = "directory"),
        @JsonSubTypes.Type(value = TextFile.class, name = "text"),
        @JsonSubTypes.Type(value = ImageFile.class, name = "image")
})
public abstract class FileSystemEntry {

    private String type;

    private FileMetadata metadata;

    public FileMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(FileMetadata metadata) {
        this.metadata = metadata;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
