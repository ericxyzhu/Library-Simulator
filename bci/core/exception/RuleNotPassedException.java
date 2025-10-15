package bci.core.exception;

public class RuleNotPassedException extends Exception {
    private int _id;

    public RuleNotPassedException (int id) {
        super("Não verificou a regra: " + id);
        _id = id;
    }

    public int getId () {
        return _id;
    }
}