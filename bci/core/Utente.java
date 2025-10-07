package bci.core;

import java.io.Serializable;

/**
 * Representa a classe Utente
 * 
 * <p>Implementa {@link Comparable} para ordenar Utentes por ordem alfabética pelos nomes, se forem iguais, ordena-se por Id.</p>
 * <p>Implementa {@link Serializable} para permitir armazenamento em ficheiro.</p>
 */
public class Utente implements Serializable, Comparable<Utente> {

    private int _id;
    private String _nome;
    private String _email;
    private boolean _atividade = true;
    private int _multa = 0;
    private tipoUtente _tipo = tipoUtente.NORMAL;

    /**
     * Construtor que inicializa um Utente já com atributos preenchidos
     * 
     * @param id Id único para cada Utente
     * @param nome nome do Utente
     * @param email email do Utente 
     */
    public Utente (int id, String nome, String email) {
        _id = id;
        _nome = nome;
        _email = email;
    }

    /**
     * Compara Utentes pelo nome de Utente, se forem iguais compara pelo Id.
     * @param utente Utente a comparar
     * @return resultado da comparação
     */
    @Override
    public int compareTo (Utente utente){
        int ret = _nome.compareTo(utente._nome);
        if (ret == 0){
            ret = _id - utente._id;
        }
        return ret;
    }

    /**
     * Devolve true se o argumento passado for um Utente e se o Id for igual ao atual
     * @param obj Objeto passado
     * @return true se Id for igual ao atual e se obj for Utente
     */
    @Override
    public boolean equals (Object obj) {
        if (!(obj instanceof Utente)) {
            return false;
        }
        return _id == ((Utente)obj)._id;
    }

    /**
     * Devolve um inteiro específico á String de nome do Utente
     * @return inteiro específico ao nome do Utente
     */
    @Override
    public int hashCode () {
        return _nome.hashCode();
    }

    /**
     * Devolve uma String com todas as informações acerca do Utente
     * @return String com informações relativas ao Utente
     */
    public String utenteString () {
        if (_atividade == true){
            return _id + " - " + _nome + " - " + _email + " - "
             + _tipo.toString() + " - ACTIVO"; 
        }
        return _id + " - " + _nome + " - " + _email + " - "
        + _tipo.toString() + " - SUSPENSO - EUR " + Integer.toString(_multa);
    }

    /**
     * Devolve o Id do Utente
     * @return Id do utente
     */
    public int getId () {
        return _id;
    }
}
