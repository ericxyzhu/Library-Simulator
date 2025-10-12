package bci.core;

public class NotificacaoDisponibilidade extends Notificacao {

    public NotificacaoDisponibilidade (Obra obra) {
        super(obra);
    }

    public String toString () {
        return "DISPONIBILIDADE: " + super.toString();
    }
}