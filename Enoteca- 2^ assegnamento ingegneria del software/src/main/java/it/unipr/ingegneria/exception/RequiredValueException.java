package it.unipr.ingegneria.exception;

public class RequiredValueException extends Exception {
    String fieldRequired;

    public RequiredValueException(String fieldRequired) {
        this.fieldRequired = fieldRequired;
    }


    public String toString() {
        return "Field required " + fieldRequired;
    }

}
