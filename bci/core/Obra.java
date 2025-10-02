package bci.core;

import java.io.Serializable;

    


public abstract class Obra implements Comparable<Obra> , Serializable { 
    private static int _qtdtotal;
    private int _id;
    private int _price;
    private int _numberOfCopies;
    private String _title;
    private Categoria _categoria;
    private int _disponiveis; // Ou então int _usados
    
    
    public Obra(String title, int price, Categoria categoria, int copies){
        _title = title;
        _price = price;
        _categoria = categoria;
        _numberOfCopies = copies;
        _disponiveis = copies;

    } // FIXME (mais para duvida) Na Uml do stor não aparece creator na classe Obra
      // FIZME (mais para duvida) Vi agora no enunciado que, nos comandos, além de dizer se uma obra é um livro ou não,
      // Mostra ainda, se for uma obra de referência, se é um dicionário, enciclpédia, etc...
      //3 - 20 de 23 - Livro - Casa Azul - 15 - Ficc¸˜ao - Jo˜ao Fonseca; Z´e Fonseca - 1234567891
      //4 - 2 de 2 - DVD - Casamento Real - 8 - Ficc¸˜ao - Ant´onio Fonseca - 200400500
      //5 - 0 de 4 - Livro - Dicion´ario - 45 - Referˆencia - Pedro Casanova - 1234567893
      //6 - 1 de 21 - Livro - Enciclop´edia - 100 - T´ecnica e Cient´ıfica - Z´e Fonseca - 1234567894
      // Tem que haver aqui um erro pois Enciclopédia não está incluído nos tecnico Cientificos
      // Página 7 do enunciado em mostrar obra
  
    @Override  
    public int compareTo (Obra obra){
        return this._title.compareTo(obra._title);

    }
    public static int getqtdtotal(){
        return _qtdtotal;
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

    String getDescription (){
        return _id + " - " + Integer.toString(_disponiveis) + " de " + Integer.toString(_numberOfCopies) + " - " + this.toString();

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
