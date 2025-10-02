package bci.core;

import java.io.Serializable;

public abstract class Work implements Serializable { 
    private static int _qtdtotal;
    private int _id;
    private int _price;
    private int _numberOfCopies;
    private String _title;
    private Categoria _categoria;
    
    
    public Obra(String title, int price, Categoria categoria, int copies){
        _title = title;
        _price = price;
        _categoria = categoria;
        _numberOfCopies = copies;

    } // FIXME Na Uml do stor não aparece creator na classe Obra

    public int getqtdtotal(){
        return this._qtdtotal;
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

    String getDescription (){

    }

    public boolean changeCopies (Work work, int copies){

    }

}
