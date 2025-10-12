package bci.core;

import java.io.Serializable;
import java.util.*;

public class Criador implements Serializable {
    private String _nome;
    private Set<Obra> _obras;
    public Criador(String nome){
        _nome = nome;
        _obras = new TreeSet <> ();
    }
    void add (Obra obra){
        _obras.add(obra);
    }
    void remove (Obra obra){
        _obras.remove(obra); //Estou a assumir que o objeto passado é igual
                             //ao que está no Set, provavelmente vamos mudar isto                          
    }

    public String getNome(){
        return _nome;
    }

    Set<Obra> obras(){
        return Collections.unmodifiableSet(_obras);
    }

    public int getNumObras () {
        return _obras.size();
    }
    
    public void removeCriador (Library library) {
        library.removeCriador(_nome);
    }
}
