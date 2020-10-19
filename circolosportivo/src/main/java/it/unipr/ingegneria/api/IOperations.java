package it.unipr.ingegneria.api;

public interface IOperations<T> {
    void subscribe(T t);
    void unscribe(T activity);
}

