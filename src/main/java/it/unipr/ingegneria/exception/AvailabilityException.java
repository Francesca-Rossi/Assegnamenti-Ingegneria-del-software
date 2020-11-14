package it.unipr.ingegneria.exception;
/**
 * This exception is called when a something is not available
 * @author Ruslan Vaslin, Nick, Francesca Rossi
 * @see java.lang.Exception
 */
public class AvailabilityException extends Exception {
    public AvailabilityException() { }


    public String toString() {
        return "Availability Exception";
    }
}
