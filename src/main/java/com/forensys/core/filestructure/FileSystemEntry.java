package com.forensys.core.filestructure;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.forensys.core.filestructure.concrete.Folder;
import com.forensys.core.filestructure.concrete.ImageFile;
import com.forensys.core.filestructure.concrete.TextFile;
import com.forensys.core.filestructure.concrete.UnknownFile;

/*
TODO: implement all file types

Behaves like idk
.chat -> chat
.url -> url
.pdf -> pdf
.xlsx -> spreadsheet
.doc -> document

Behaves like TextFile
.txt -> plaintext
.md -> markdown 
.log -> log
.csv -> csv
.conf -> config
.java -> java
.tmp -> temporary
.dat -> data

Behaves like Folder
directory
.zip -> archive

Behaves like ImageFile
.png -> image
.jpg -> image
.gif -> image
*/
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type",
    visible = true,
    defaultImpl = UnknownFile.class
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Folder.class, name = "directory"),
    @JsonSubTypes.Type(value = Folder.class, name = "archive"),

    @JsonSubTypes.Type(value = TextFile.class, name = "plaintext"),
    @JsonSubTypes.Type(value = TextFile.class, name = "markdown"),
    @JsonSubTypes.Type(value = TextFile.class, name = "log"),
    @JsonSubTypes.Type(value = TextFile.class, name = "csv"),
    @JsonSubTypes.Type(value = TextFile.class, name = "config"),
    @JsonSubTypes.Type(value = TextFile.class, name = "java"),
    @JsonSubTypes.Type(value = TextFile.class, name = "temporary"),
    @JsonSubTypes.Type(value = TextFile.class, name = "data"),

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
