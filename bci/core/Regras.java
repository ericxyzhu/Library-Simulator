package bci.core;

import java.io.Serializable;

public abstract class Regras implements Comparable<Regras>, Serializable {
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
