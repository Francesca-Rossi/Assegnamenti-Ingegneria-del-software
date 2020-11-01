package it.unipr.ingegneria.repo;

public interface CommonOperations<T> {
    void add(T... items);
    void remove(T... items) throws Exception;
}
