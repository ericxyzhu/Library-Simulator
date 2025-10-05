package bci.core;

public enum Categoria {
    REFERENCE("Referência"), FICTION("Ficção"), SCITECH("Técnica e Ciêntífica");
    private String _categoria;
    private Categoria (String categoria){
        _categoria = categoria;
    }
    public String toString(){
        return _categoria;
    }

}
