package bci.core;

import java.util.List;
import java.util.ArrayList;


public class Livro extends Obra {
    private String _isbn;
    private List<Criador> _creators;


    public Livro (String title, int price, Categoria categoria, int copies, String isbn, List<Criador> creators){
        super(title, price, categoria, copies);
        _isbn = isbn;
        this._creators = new ArrayList <> (creators);

    }

    public String toString(){
        return "Livro -" + super.gettitle() + " - " + super.getprice() + " - " + super.getcategoria() + " - " + this.getAllCriadores() + " - " + _isbn;

    }

    public String getAllCriadores(){
        List<String> criadoresNome = new ArrayList<>();
        for(int i = 0; i < _creators.size(); i++){
            Criador c = _creators.get (i);
            if ( i == (_creators.size() - 1) )
                criadoresNome.add(c.getnome());
            else
                criadoresNome.add(c.getnome() + "; ");
        }
        return String.join("", criadoresNome);
    }


}
