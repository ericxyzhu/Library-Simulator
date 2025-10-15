package bci.core.exception;

public class WorkNotAvailableException extends Exception {
    int _id;

    public WorkNotAvailableException (int id) {
        super("Não verificou a regra: " + id);
        _id = id;
    }

    public int getId () {
        return _id;
    }
}