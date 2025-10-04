package bci.core;


public class Dvd extends Obra {
    private String _igac;
    private Criador _realizador;

    
    public Dvd (String title, int price, Categoria categoria, int copies, String igac, Criador realizador){
        super(title, price, categoria, copies);
        _realizador = realizador;
        _igac = igac;

    }

    public Criador getcriador(){
        return _realizador;
    }

    public String toString(){
        return "Dvd -" + super.gettitle() + " - " + super.getprice() + " - " + super.getcategoria() + _realizador.getnome() + " - " + _igac ;

    }





}
