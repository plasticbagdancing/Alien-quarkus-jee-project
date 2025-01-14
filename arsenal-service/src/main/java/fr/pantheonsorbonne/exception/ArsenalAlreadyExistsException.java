package fr.pantheonsorbonne.exception;

public class ArsenalAlreadyExistsException extends Exception {
    public ArsenalAlreadyExistsException() {
        super("An arsenal with the same type already exists.");
    }
}
