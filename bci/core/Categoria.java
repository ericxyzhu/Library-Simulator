package bci.core;

/**
 * Representa o enumerado Categoria, onde estão todas as Categorias possíveis para uma Obra
 */
public enum Categoria {
    REFERENCE("Referência"),
    FICTION("Ficção"),
    SCITECH("Técnica e Científica");

    private String _categoria;

    /**
     * Construtor privado para um Categoria
     * @param categoria Categoria que vai ser guardada na Obra
     */
    private Categoria (String categoria){
        _categoria = categoria;
    }

    /**
     * Devolve a String correspondente ao nome da Categoria
     * @return String da Categoria
     */
    public String toString(){
        return _categoria;
    }

}
