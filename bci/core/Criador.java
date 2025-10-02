package bci.core;

import java.util.*;

public class Criador {
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
    public String getnome(){
        return _nome;
    }
    Set<Obra> obras(){
        return _obras;
    }

    
}
