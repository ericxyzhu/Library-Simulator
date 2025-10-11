package bci.core;

public class RegraUtenteActive extends Regras {
    public RegraUtenteActive(){
        super(2);
    }
    public boolean verificar (Utente utente, Obra obra){
        return utente.getAtividade();
    }
}
