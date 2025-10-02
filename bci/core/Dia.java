package bci.core;

import java.io.Serializable;

/**
 * Class that represents the date (an integer starting from 1)
 */
public class Dia implements Serializable {
    private int _dia;

    public Dia () {
        this (1);
    }

    public Dia (int dia) {
        _dia = dia;
    }

    public int getDia () {
        return _dia;
    }

    public String diaString () {
        return Integer.toString(_dia);
    }

    protected void avanca (int n) {
        if (n > 0) _dia += n;
    }
}
