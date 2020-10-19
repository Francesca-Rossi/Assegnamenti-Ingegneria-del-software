package it.unipr.ingegneria.api;

public interface IOperationsPrevileged<T, C> extends IOperations<C> {

    void addUser(T t);
    void deleteUser(T t);

    void addActivity(C c);
    void deleteActivity(C c);
}
