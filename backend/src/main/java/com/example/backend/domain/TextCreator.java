package com.example.backend.domain;

@FunctionalInterface
public interface TextCreator<T, U, R, S> {

    S create(T t, U u, R r);
}
