package bci.core.exception;

public class CreatorNotFoundException extends Exception {
    public CreatorNotFoundException (String nome){
        super("Não se encontra o Criador: " + nome);
    }


}
