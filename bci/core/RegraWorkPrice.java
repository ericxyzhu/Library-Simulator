package bci.core;

public class RegraWorkPrice extends Regras{
    public RegraWorkPrice(){
        super(6);
    }
    public boolean verificar (Utente utente, Obra obra){
        if (obra.getPrice() <= 25) return true;
        if (utente.getTipo() == TipoUtenteCumpridor.CUMPRIDOR) return true;
        return false;
    }

}
