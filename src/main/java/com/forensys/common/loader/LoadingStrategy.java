package com.forensys.common.loader;

public interface LoadingStrategy<T> {
    T load(String sourceData);
}
