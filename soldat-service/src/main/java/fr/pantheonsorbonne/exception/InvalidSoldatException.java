package fr.pantheonsorbonne.exception;

public class InvalidSoldatException extends Throwable {
    public InvalidSoldatException(String categorieIsInvalid) {
        super(categorieIsInvalid);
    }
}
