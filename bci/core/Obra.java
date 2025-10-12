package bci.core;

import bci.core.exception.*;
import java.io.Serializable;
import java.util.*;
    

/**
 * Representa a classe Obra, serve como base Livro e Dvd.
 * 
 * <p>Implementa {@link Comparable} para ordenar as Obras por ordem crescente pelos títulos.</p>
 * <p>Implementa {@link Serializable} para permitir armazenamento em ficheiro. </p>
 * @see Livro
 * @see Dvd
 * 
 */
public abstract class Obra implements Comparable<Obra> , Serializable { 
    private int _id;
    private int _price;
    private int _numberOfCopies;
    private String _title;
    private Categoria _categoria;
    private int _disponiveis; 
    private Map<Integer, Utente> _notifDisp = new HashMap<>();
    private Map<Integer, Utente> _notifRequis = new HashMap<>();

    /**
     * Construtor que inicializa uma Obra já com atributos preenchidos
     * 
     * @param id Id único para cada Obra
     * @param title título da Obra 
     * @param price preço da Obra
     * @param categoria Categoria da Obra
     * @param copies número de cópias da Obra
     */
    public Obra(int id, String title, int price, Categoria categoria, int copies){
        _id = id;
        _title = title;
        _price = price;
        _categoria = categoria;
        _numberOfCopies = copies;
        _disponiveis = copies;

    }

    /**
     * Compara Obras pelo título alfabeticamente
     * @param obra Obra a comparar
     * @return resultado da comparação entre os títulos
     */
    @Override  
    public int compareTo (Obra obra){
        int ret = _title.toLowerCase().compareTo(obra._title.toLowerCase());
        if (ret == 0) {
            ret = _id - obra._id;
        }
        return ret;
    }


    /**
     * Devolve o preço da Obra
     * @return preço da Obra
     */
    public int getPrice(){
        return this._price;
    }

    /**
     * Devolve o número de cópias da Obra
     * @return número de cópias
     */
    public int getCopies(){
        return this._numberOfCopies;
    }

    /**
     * Devolve o número de exemplares disponíveis da Obra
     * @return número de exemplares disponíveis
     */
    public int getDisponiveis(){
        return this._disponiveis;
    }

    /**
     * Devolve o título da Obra
     * @return título da Obra
     */
    public String getTitle(){
        return this._title;
    }

    /**
     * Devolve o Id da Obra
     * @return Id da Obra
     */
    public int getId(){
        return this._id;
    }

    /**
     * Devolve a Categoria da Obra
     * @return Categoria da Obra
     */
    public Categoria getCategoria(){
        return this._categoria;

    }

    /**
     * Devolve uma String com informações específicas de acordo com a subclasse de Obra
     * @return String com as informações
     */
    public abstract String toString();
    
    public abstract String getCriadores();

    public abstract boolean searchSpecificObras(String termo);

    /**
     * Devolve uma String com a descrição da Obra
     * @return String com descrição
     */
    public String getDescription (){
        return Integer.toString(_id) + " - " + Integer.toString(_disponiveis) + " de " + Integer.toString(_numberOfCopies) + " - " + this.toString();

    }


    /**
     * Altera o número de cópias da Obra
     * @param obra Obra que queremos alterar
     * @param copies número a adicionar ou a subtrair ao número de cópias
     * @throws Exception se o número de cópias, ou o número de cópias disponivel ficar negativo
     */
    public void changeCopies (int copies){
        _numberOfCopies += copies;
        _disponiveis += copies;   
    }

    public void addNotifDisp (int id, Library library) throws UserNotFoundException {
        _notifDisp.putIfAbsent(id, library.getUtente(id));
    }

    public void addNotifRequis (int id, Library library) throws UserNotFoundException {
        _notifRequis.putIfAbsent(id, library.getUtente(id));
    }

    public void sendNotifDisp () {
        Notificacao notif = new NotificacaoDisponibilidade(this);
        for (Utente utente : _notifDisp.values()) {
            utente.addNotif(notif);
        }
    }

    public void sendNotifRequis () {
        Notificacao notif = new NotificacaoRequisicao(this);
        for (Utente utente : _notifRequis.values()) {
            utente.addNotif(notif);
        }
    }

    public void removeNotifDisp (int id) {
        _notifDisp.remove(id);
    }
}

    
