package com.jayfella.devkit.properties.builder;

import com.jayfella.devkit.properties.ComponentSetBuilder;

public abstract class AbstractComponentSetBuilder<T> implements ComponentSetBuilder<T> {

    protected final T object;
    protected final String[] ignoredProperties;

    public AbstractComponentSetBuilder(T object, String... ignoredProperties) {
        this.object = object;
        this.ignoredProperties = ignoredProperties;
    }

}
