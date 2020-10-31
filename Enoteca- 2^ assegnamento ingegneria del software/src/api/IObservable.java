package api;

import entities.User;

public interface IObservable <T, C> {
    void addObserver(T t);
    void removeObserver(T t);
    void avaible(C c);
    void finish();
    void allarm(C c, T t);
}
