package it.unipr.ingegneria.exception;

public class NotFoundException extends Exception {
    public NotFoundException() { }


    public String toString() {
        return "Not Found";
    }
}
