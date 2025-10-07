package bci.core;

import java.io.Serializable;

/**
 * Representa a classe Dia, onde vamos guardar a data da Biblioteca
 */
public class Dia implements Serializable {
    private int _dia;

    /**
     * Construtor do Dia, sem argumentos a data começa sempre dia 1
     */
    public Dia () {
        this (1);
    }

    /**
     * Construtor do Dia
     * @param dia número do dia inicial
     *    
     */
    public Dia (int dia) {
        _dia = dia;
    }

    /**
     * Devolve o dia atual
     * @return número que representa o Dia
     */
    public int getDia () {
        return _dia;
    }

    /**
     * Devolve o dia atual em String
     * @return String do dia
     */
    public String diaString () {
        return Integer.toString(_dia);
    }

    /**
     * Avanca a data n dias
     * @param n número de dias a avançar
     */
    protected void avanca (int n) {
        if (n > 0) _dia += n;
    }
}
