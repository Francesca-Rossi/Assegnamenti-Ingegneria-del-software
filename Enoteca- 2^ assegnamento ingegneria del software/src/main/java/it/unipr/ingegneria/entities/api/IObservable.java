package it.unipr.ingegneria.entities.api;

public interface IObservable  <T, C> {
    void addObserver(T t);
    void removeObserver(T t);
    void avaible(C c);
    void finish();
    void allarm(C c, T t);
}
