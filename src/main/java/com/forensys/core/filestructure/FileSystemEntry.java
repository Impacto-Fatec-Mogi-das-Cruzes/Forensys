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
    defaultImpl = UnknownFile.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Directory.class, name = "directory"),
        @JsonSubTypes.Type(value = TextFile.class, name = "text"),
        @JsonSubTypes.Type(value = ImageFile.class, name = "image")
})
public abstract class FileSystemEntry {

    private FileMetadata metadata;

    public FileMetadata getMetadata() {
        return metadata;
    }

}
