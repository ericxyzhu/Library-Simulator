package bci.core;

public class RegraWorkHasAvailableCopies extends Regras {
    public RegraWorkHasAvailableCopies(){
        super (3);
    }
    public boolean verificar(Utente utente, Obra obra){
        if (obra.getDisponiveis() <= 0) return false;
        return true;
    }

}
