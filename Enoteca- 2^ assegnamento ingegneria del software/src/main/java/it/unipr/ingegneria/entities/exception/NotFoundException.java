package it.unipr.ingegneria.entities.exception;

public class NotFoundException extends Exception {
    public NotFoundException() { }


    public String toString() {
        return "Not Found";
    }
}
