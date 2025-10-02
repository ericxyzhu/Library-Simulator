package bci.core;

import java.io.Serializable;

public abstract class Obra implements Serializable { 
    private static int _qtdtotal;
    private int _id;
    private int _price;
    private int _numberOfCopies;
    private String _title;
    private Categoria _categoria;
    private int _disponiveis; 
    
    
    public Obra(String title, int price, Categoria categoria, int copies){
        _title = title;
        _price = price;
        _categoria = categoria;
        _numberOfCopies = copies;

    } // FIXME Na Uml do stor não aparece creator na classe Obra

    public static int getqtdtotal(){
        return _qtdtotal;
    }

    public int getprice(){
        return this._price;
    }

    public int getcopies(){
        return this._numberOfCopies;
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

    String getDescription (){
        return _id + " - " + Integer.toString(_disponiveis) + " de " + Integer.toString(_numberOfCopies) + " - " + this.toString();

    }

    public boolean changeCopies (Obra obra, int copies){

    }

}
