package bci.core;

import java.io.Serializable;

public class Requisicao implements Serializable {

    private Obra _obra;
    private int _deadline;

    public Requisicao (Obra obra, int deadline) {
        _obra = obra;
        _deadline = deadline;
    }
}