package com.forensys.common.loader;

public abstract class Loader<T> {
    
    private final LoadingStrategy<T> loadingStrategy;

    protected Loader(LoadingStrategy<T> loadingStrategy) {
        this.loadingStrategy = loadingStrategy;
    }

    public T load(String sourceData) {
        return getLoadingStrategy().load(sourceData);
    };

    public LoadingStrategy<T> getLoadingStrategy() {
        return loadingStrategy;
    }
}
