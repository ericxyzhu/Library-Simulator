package bci.core;

import java.io.Serializable;

public class Utente implements Serializable {

    private int _id;
    private String _nome;
    private String _email;
    private boolean _atividade = true;
    private int _multa = 0;
    private tipoUtente _tipo = tipoUtente.NORMAL;

    public Utente (int id, String nome, String email) {
        _id = id;
        _nome = nome;
        _email = email;
    }

    public String utenteString () {
        if (_atividade == true){
            return _id + " - " + _nome + " - " + _email + " - "
             + _tipo.toString() + " - ACTIVO"; 
        }
        return _id + " - " + _nome + " - " + _email + " - "
        + _tipo.toString() + " - SUSPENSO - EUR " + Integer.toString(_multa);
    }
}
