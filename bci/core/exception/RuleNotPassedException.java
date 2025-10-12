package bci.core.exception;

public class RuleNotPassedException extends Exception {
    public RuleNotPassedException (int id) {
        super("Não verificou a regra: " + id);
    }
}