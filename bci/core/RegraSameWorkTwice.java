package bci.core;

public class RegraSameWorkTwice extends Regras{
    public RegraSameWorkTwice(){
        super(1);
    }
    public boolean verificar (Utente utente, Obra obra){
        if(utente.alreadyContainsWork(obra) == true) return false;
        return true;
     
    }

}
