package it.unipr.ingegneria.exception;
/**
 * Custom exception to manage the empty list
 *
 * @since 1.0
 * @author Ruslan Vasyunin, Nick, Francesca Rossi
 * @version 1.0
 *
 */
public class ListEmptyException extends  NullPointerException {
    public ListEmptyException() {
    }

    public ListEmptyException(String message) {
        super("ListEmptyException: "+message);
    }
}
