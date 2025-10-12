package bci.core;

import java.io.Serializable;

public abstract class Notificacao implements Serializable {
    private Obra _obra;

    public Notificacao (Obra obra) {
        _obra = obra;
    }

    public String toString () {
        return _obra.toString();
    }
}