package it.unipr.ingegneria.api;
/**
 * The  {@code IObservable} is use to implement the pattern observer.
 * @author Francesca Rossi, Everton Ejike, Ruslan Vasyunin
 *
 */
public interface IObservable  <T> {
    /**
     * Method to add an observer
     * @param t
     */
    void addObserver(T t);

    /**
     * Method to remove an observer
     * @param t
     */
    void removeObserver(T t);

}
