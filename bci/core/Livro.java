package bci.core;

import java.util.List;
import java.util.ArrayList;


public class Livro extends Obra {
    private String _isbn;
    private List<Criador> _creators;


    public Livro (int id, String title, int price, Categoria categoria, int copies, String isbn, List<Criador> creators){
        super(id, title, price, categoria, copies);
        _isbn = isbn;
        this._creators = new ArrayList <> (creators);

    }

    public String toString(){
        return "Livro - " + super.getTitle() + " - " + super.getPrice() + " - " + super.getCategoria().toString() + " - " + this.getAllCriadores() + " - " + _isbn ;

    }

    public String getAllCriadores(){
        String ret = new String();
        for(int i = 0; i < _creators.size(); i++){
            Criador c = _creators.get (i);
            if ( i == (_creators.size() - 1) )
                ret += c.getNome();
            else
                ret += c.getNome() + "; ";

        }
        return ret;
    }


}
