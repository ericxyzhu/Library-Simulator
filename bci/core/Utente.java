package bci.core;

import java.io.Serializable;
import java.util.*;

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
    private TipoUtente _tipo = TipoUtenteNormal.NORMAL;
    private Map<Integer, Requisicao> _requisicoes = new HashMap<>();
    private String _notificacoes = new String();
    private int _numForaPrazo = 0;
    private int _credit = 0;

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

    public String getEmail () {
        return _email;
    }

    public boolean getAtividade () {
        return _atividade;
    }

    public void setAtividade (boolean bool) {
        _atividade = bool;
    }

    public void limpaMulta () {
        _multa = 0;
    }

    public void addMulta (int qtd) {
        _multa += qtd;
    }

    public int getMulta () {
        return _multa;
    }

    public void setTipo (TipoUtente tipo) {
        _tipo = tipo;
    }

    public void addRequis (Obra obra, int deadline) {
        Requisicao requis = new Requisicao(obra, deadline);
        _requisicoes.put(obra.getId(), requis);
    }

    public void removeRequis (int obraId) {
        _requisicoes.remove(obraId);
    }

    public boolean hasRequis (int obraId) {
        return _requisicoes.containsKey(obraId);
    }

    public Requisicao getRequis (int id) {
        return _requisicoes.get(id);
    }

    public int getNumObrasRequisitadas (){
        return this._requisicoes.size();
    }

    public TipoUtente getTipo () {
        return this._tipo;
    }

    public void updateTipo () {
        switch (_credit) {
            case -3 : 
                _tipo = TipoUtenteFaltoso.FALTOSO;
                break;
            case 3 :
                _tipo = TipoUtenteNormal.NORMAL;
                break;
            case 5 :
                _tipo = TipoUtenteCumpridor.CUMPRIDOR;
                break;
            case -1 :
                if (_tipo.equals(TipoUtenteCumpridor.CUMPRIDOR)) {
                    _tipo = TipoUtenteNormal.NORMAL;
                }
                break;
        }
    }

    public boolean alreadyContainsWork(Obra obra){
        for (Requisicao requisicao: _requisicoes.values()){
            if (requisicao.getObra().getId() == obra.getId())
                return true;
        }
        return false;
    }

    public void addNotif (String notif) {
        if (_notificacoes.length() != 0) _notificacoes += "\n";
        _notificacoes += notif;
    }

    public void clearNotifs () {
        _notificacoes = new String();
    }

    public String getAllNotifString () {
        return _notificacoes;
    }

    public void changeNumForaPrazo (int num) {
        _numForaPrazo += num;
    }

    public int getNumForaPrazo () {
        return _numForaPrazo;
    }

    public int getCredit () {
        return _credit;
    }

    public void setCredit (int qtd) {
        _credit = qtd;
    }

    public void changeCredit (int qtd) {
        _credit += qtd;
    }

    public void updateEstado (Library library) {
        int today = library.getData().getDia();
        int numForaPrazo = 0;
        for (Requisicao requis : _requisicoes.values()) {
            int deadline = requis.getDeadline();
            if (today > deadline) {
                _atividade = false;
                numForaPrazo++;
            }
        }
        _numForaPrazo = numForaPrazo;
    }

    public static Collection<Utente> orderByEmail (Collection<Utente> utentes) {
        List<Utente> list = new ArrayList<>();
        list.addAll(utentes);
        list.sort(new UtenteCmpEmail());
        return Collections.unmodifiableCollection(list);
    }

}

    
