package bci.core;

import java.io.Serializable;

    


public abstract class Obra implements Comparable<Obra> , Serializable { 
    private int _id;
    private int _price;
    private int _numberOfCopies;
    private String _title;
    private Categoria _categoria;
    private int _disponiveis; // Ou então int _usados
    
    
    public Obra(int id, String title, int price, Categoria categoria, int copies){
        _id = id;
        _title = title;
        _price = price;
        _categoria = categoria;
        _numberOfCopies = copies;
        _disponiveis = copies;

    }

    @Override  
    public int compareTo (Obra obra){
        return this._title.compareTo(obra._title);

    }

    public int getprice(){
        return this._price;
    }

    public int getcopies(){
        return this._numberOfCopies;
    }

    public int getdisponiveis(){
        return this._disponiveis;
    }

    public String gettitle(){
        return this._title;
    }

    public int getid(){
        return this._id;
    }

    public Categoria getcategoria(){
        return this._categoria;

    }
    
    public abstract String toString();

    public String getDescription (){
        return Integer.toString(_id) + " - " + Integer.toString(_disponiveis) + " de " + Integer.toString(_numberOfCopies) + " - " + this.toString();

    }

    public void changeCopies (Obra obra, int copies) throws Exception /* Inválido número de cópias exception */ {
        if (copies >= 0){
            _numberOfCopies += copies;
            _disponiveis += copies;
        } else {
            if ((_disponiveis -= copies) < 0)
               // throw new InvalidException("Número de cópias inválido");
            _disponiveis = 0;
            _numberOfCopies -= copies;
        }

    }

}
