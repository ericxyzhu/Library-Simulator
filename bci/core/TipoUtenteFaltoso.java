package bci.core;

public class TipoUtenteFaltoso extends TipoUtente {

    public static final TipoUtente FALTOSO = new TipoUtenteFaltoso();

    public String toString () {
        return "FALTOSO";
    }

    public int prazo (Obra obra) {
        return 2;
    }

    public int maxNumRequis () {
        return 1;
    }

    public boolean canRequisMoreThan25Euro () {
        return false;
    }
}