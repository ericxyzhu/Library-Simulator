package bci.core;

public class RegraUtenteMaxWork extends Regras{
    public RegraUtenteMaxWork(){
        super(4);
    }
    public boolean verificar (Utente utente, Obra obra){
        if (utente.getNumObrasRequisitadas() == utente.getTipo().maxNumRequis()) return false;
        return true;
    }

}
