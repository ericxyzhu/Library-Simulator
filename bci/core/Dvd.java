package bci.core;

import java.util.List;

public class Dvd extends Obra {
    private String _igac;
    private Criador _realizador;

    public Dvd (String title, int price, Categoria categoria, int copies, String igac, Criador realizador){
        super(title, price, categoria, copies);
        _realizador = realizador;
        _igac = igac;

    }

    public String toString(){
        return "Dvd -" + super.gettitle() + " - " + super.getprice() + " - " + super.getcategoria() + " - " + "Criador.toString() _realizador" + " - " + _igac;

    }





}
