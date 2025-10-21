package bci.core;

import bci.core.exception.RuleNotPassedException;
import bci.core.exception.WorkNotAvailableException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RegrasManager implements Serializable {
    private List<Regras> _regras = new ArrayList<>();

    public RegrasManager () {
    _regras.add(new RegraSameWorkTwice());
    _regras.add(new RegraUtenteActive());
    _regras.add(new RegraUtenteMaxWork());
    _regras.add(new RegraWorkCategory());
    _regras.add(new RegraWorkHasAvailableCopies());
    _regras.add(new RegraWorkPrice());
    }

    public void verificaRegras (Utente utente, Obra obra) throws RuleNotPassedException, WorkNotAvailableException {
        for (Regras regra : _regras) {
            if (regra.verificar(utente, obra) == false) {
                if (regra.getId() != 3) {
                    throw new RuleNotPassedException(regra.getId());
                } else {
                    throw new WorkNotAvailableException(regra.getId());
                }
            }
        }
    } 
}