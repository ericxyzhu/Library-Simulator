package bci.core.exception;

public class RequisNotFoundException extends Exception {
    public RequisNotFoundException (int utenteId, int obraId) {
        super("Não se encontra a requisição com utente " + utenteId + " e obra " + obraId);
    }
}