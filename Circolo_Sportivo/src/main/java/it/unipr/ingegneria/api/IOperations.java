package it.unipr.ingegneria.api;

/**
 * Generic interface for the main operations implemented in entities
 * @param <T> Generic parameter for the method of the interface
 * @author Ruslan, Francesca, Nick
 */
public interface IOperations<T> {
    /**
     * Subscribe in a certain data
     * @param t
     */
    void subscribe(T t);
    /**
     * unsubscribe in a certain data
     * @param t
     */
    void unsubscribe(T t);



}
