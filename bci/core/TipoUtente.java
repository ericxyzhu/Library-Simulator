package bci.core;

import java.io.Serializable;

public abstract class TipoUtente implements Serializable {

    public abstract String toString ();

    public abstract int prazo (Obra obra);

    public abstract int maxNumRequis ();

    public abstract boolean canRequisMoreThan25Euro ();

    public boolean equals (TipoUtente tipo) {
        return tipo.toString() == toString();
    }

}