package br.com.mk.executor;

public interface Executor<T> {
    T execute(T input);
}