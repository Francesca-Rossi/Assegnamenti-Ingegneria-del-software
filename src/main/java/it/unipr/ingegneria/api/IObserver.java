package it.unipr.ingegneria.api;
/**
 * The  {@code IObserver}  is use to implement the pattern observer.
 * @author Francesca Rossi, Everton Ejike, Ruslan Vasyunin
 *
 */
public interface IObserver {
    /**
     * Method to update the state
     * @param o
     */
    void update(Object o);
}
