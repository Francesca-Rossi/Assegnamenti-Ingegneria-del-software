package it.unipr.ingegneria.exception;
/**
 * This exception is called when something is not found
 * @author Ruslan Vaslin, Nick, Francesca Rossi
 * @see java.lang.Exception
 */
public class NotFoundException extends Exception {
    public NotFoundException() { }


    public String toString() {
        return "Not Found";
    }
}
