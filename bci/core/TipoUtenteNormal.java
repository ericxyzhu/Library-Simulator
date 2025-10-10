package bci.core;

public class TipoUtenteNormal extends TipoUtente {

    public static final TipoUtente NORMAL = new TipoUtenteNormal();

    public String toString () {
        return "NORMAL";
    }

    public int prazo (Obra obra) {
        if (obra.getCopies() == 1) {
            return 3;
        }
        if (obra.getCopies() <= 5) {
            return 8;
        }
        return 15;
    }

    public int maxNumRequis () {
        return 3;
    }

    public boolean canRequisMoreThan25Euro () {
        return false;
    }
}