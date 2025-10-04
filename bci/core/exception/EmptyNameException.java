package bci.core.exception;

public class EmptyNameException extends Exception {
    public EmptyNameException () {
        super ("Nome ou Email não pode ser vazio");
    }
}
