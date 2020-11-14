package it.unipr.ingegneria.exception;
/**
 * This exception is called when a value is required but is not present
 * @author Ruslan Vaslin, Nick, Francesca Rossi
 * @see java.lang.Exception
 */
public class RequiredValueException extends Exception {
    String fieldRequired;

    public RequiredValueException(String fieldRequired) {
        this.fieldRequired = fieldRequired;
    }


    public String toString() {
        return "Field required " + fieldRequired;
    }

}
