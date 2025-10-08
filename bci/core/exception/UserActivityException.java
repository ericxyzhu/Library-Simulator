package bci.core.exception;

public class UserActivityException extends Exception {
    public UserActivityException (int id){
        super("Utente " + id + " não está suspenso");
    }
}