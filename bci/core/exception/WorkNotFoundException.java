package bci.core.exception;

public class WorkNotFoundException extends Exception {
    public WorkNotFoundException (int id){
        super("Não se encontra a Obra: " + id);
    }

}
