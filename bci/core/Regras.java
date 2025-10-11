package bci.core;

// import bci.app.exception.BorrowingRuleFailedException;

public abstract class Regras implements Comparable<Regras>{
    public int _id;
    public Regras(int id){
        _id = id;
    }
    public int compareTo (Regras regra){
        return  _id - regra._id;
    }
    public abstract boolean verificar(Utente utente, Obra obra);
    public int getId(){
        return _id;
    }
}
