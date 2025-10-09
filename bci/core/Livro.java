package bci.core;

import java.util.List;
import java.util.ArrayList;

/**
 * Extende {@link Obra} com atributos específicos a Livro
 * 
 * @see Obra
 */
public class Livro extends Obra {
    private String _isbn;
    private List<Criador> _creators;

    /**
     * Construtor que cria um Livro com atributos preenchidos,
     * usa o construtor da Obra para as informações gerais para todas as Obras
     * 
     * @param id Id específico para o Livro (específico para cada Obra)
     * @param title título para o Livro
     * @param price preço do Livro
     * @param categoria Categoria do Livro
     * @param copies número de cópias do Livro
     * @param isbn isbn do Livro
     * @param creators Lista de Criadores do Livro
     */
    public Livro (int id, String title, int price, Categoria categoria, int copies, String isbn, List<Criador> creators){
        super(id, title, price, categoria, copies);
        _isbn = isbn;
        this._creators = new ArrayList <> (creators);

    }

    /**
     * {@inheritDoc}
     */
    public String toString(){
        return "Livro - " + super.getTitle() + " - " + super.getPrice() + " - " + super.getCategoria().toString() + " - " + this.getCriadores() + " - " + _isbn ;

    }

    /**
     * Devolve uma String com os nomes dos Criadores do Livro
     * @return String com os nomes dos Criadores
     */
    public String getCriadores(){
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

    public boolean searchSpecificObras(String termo){
        if (super.getTitle().toLowerCase().contains(termo.toLowerCase()))
            return true;
        for (Criador criador: _creators){
            if(criador.getNome().toLowerCase().contains(termo.toLowerCase()))
                return true;
        }
        return false;
    }
}
