package it.unipr.ingegneria.exception;

/**
 * Custom exception to manage the element already present in a list
 * @since 1.0
 * @author Ruslan Vasyunin, Nick, Francesca Rossi
 * @version 1.0
 */
public class ElementAlreadyPresentException extends  IllegalArgumentException {
    public ElementAlreadyPresentException() {
    }

    public ElementAlreadyPresentException(String message) {
        super("ElementAlreadyPresentException: "+message);
    }
}