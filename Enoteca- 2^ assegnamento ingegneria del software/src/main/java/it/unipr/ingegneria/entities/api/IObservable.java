package it.unipr.ingegneria.entities.api;

public interface IObservable  <T> {
    void addObserver(T t);
    void removeObserver(T t);

}
