package bci.core;

public class TipoUtenteCumpridor extends TipoUtente {

    public static final TipoUtente CUMPRIDOR = new TipoUtenteCumpridor();

    public String toString () {
        return "CUMPRIDOR";
    }

    public int prazo (Obra obra) {
        if (obra.getCopies() == 1) {
            return 8;
        }
        if (obra.getCopies() <= 5) {
            return 15;
        }
        return 30;
    }

    public int maxNumRequis () {
        return 5;
    }

    public boolean canRequisMoreThan25Euro () {
        return true;
    }
}