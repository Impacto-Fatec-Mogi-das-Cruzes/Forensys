package com.forensys.core.filestructure;

public record FileMetadata(
    String name,
    String password,
    boolean blocked,
    boolean hidden,
    String description,
    int size,
    String created,
    String updated,
    boolean readOnly
) {}