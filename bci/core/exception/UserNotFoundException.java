package bci.core.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException (int id) {
        super("Não se encontra o Utente: " + id);
    }
}
