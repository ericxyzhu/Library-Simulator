package bci.core;

public class RegraWorkCategory extends Regras {
    public RegraWorkCategory(){
        super(5);
    }
    public boolean verificar (Utente utente, Obra obra){
        if (obra.getCategoria() == Categoria.REFERENCE) return false;
        return true;
    }

}
