package bci.core;

public class NotificacaoRequisicao extends Notificacao {

    public NotificacaoRequisicao (Obra obra) {
        super(obra);
    }

    public String toString () {
        return "REQUISIÇÃO: " + super.toString();
    }
}