package bci.core;

import bci.core.exception.*;
import java.io.*;
import java.util.*;

/**
 * Class that represents the library as a whole.
 */
public class Library implements Serializable {

  /** Serial number for serialization. */
  @Serial
  private static final long serialVersionUID = 202501101348L;

  private int _nextUtenteId = 1;
  private int _nextObraId = 1;
  private int _numUtentes = 0;
  private int _numObras = 0;
  private Dia _dia = new Dia();
  private boolean _hasFilename = false;
  private Parser _parser = new Parser(this);
  private String _filename;
  private boolean _isModified = false;
  private Map<Integer, Obra> _obras;
  private Set<Utente> _utentes;
  private Map<String, Criador> _criadores;
  private List<Regras> _regras = new ArrayList<>();
  
  /**
   * Construtor que inicializa uma nova biblioteca vazia
   * Usa coleções para armazenar os utentes, as obras e criadores
   */
  public Library () {
    _obras = new TreeMap<>();
    _utentes = new TreeSet<>();
    _criadores = new TreeMap<>();
    _regras.add(new RegraSameWorkTwice());
    _regras.add(new RegraUtenteActive());
    _regras.add(new RegraUtenteMaxWork());
    _regras.add(new RegraWorkCategory());
    _regras.add(new RegraWorkHasAvailableCopies());
    _regras.add(new RegraWorkPrice());
  }

  /**
   * Obtém uma cópia da data atual do sistema
   * 
   * @return instância do tipo Dia com a data atual
   */
  
  public Dia getData () {
    return new Dia(_dia.getDia());
  }

  /**
   * Avança data do sistema n dias
   * 
   * @param n número de dias a avançar
   */
  public void avancaData (int n) {
    _dia.avanca(n);
  }

  /**
   * Altera em Biblioteca se esta tem um nome de ficheiro ou não
   * @param bool true se tiver, false se não tiver
   */
  void setHasFilename(boolean bool){
    _hasFilename = bool;
  }

  /**
   * Guarda nome do ficheiro para a respetiva biblioteca
   * @param filename nome do ficheiro
   */
  void setFilename (String filename) {
    _filename = filename;
  }

  /**
   * Devolve o booleano sobre se a Biblioteca ao não tem nome de ficheiro
   * @return bool que confirma se Biblioteca tem ou não tem nome de ficheiro
   */
  public boolean getHasFilename(){
    return _hasFilename;
  }
  /**
   * Obtém o nome do ficheiro para a respetiva biblioteca
   * 
   * @return nome do ficheiro
   */
  public String getFilename () {
    return _filename;
  }

  /**
   * Devolve o booleano sobre se a Biblioteca foi modificada
   * @return bool que confirma se a biblioteca foi modificada
   */
  public boolean getIsModified () {
    return _isModified;
  }

  /**
   * Altera o estado da biblioteca sobre se está modificada
   * @param bool o novo estado da biblioteca sobre se está modificado
   */
  public void setIsModified (boolean bool) {
    _isModified = bool;
  }

  /**
   * Regista um Utente na Biblioteca
   * 
   * @param nome nome do Utente
   * @param email email do Utente 
   * @return Id do respetivo Utente
   * @throws EmptyNameException se o nome ou email do Utente estiverem vazios 
   */
  public int registaUtente (String nome, String email) throws EmptyNameException {
    if (nome.length() == 0 || email.length() == 0){
      throw new EmptyNameException();
    } else {
      Utente utente = new Utente(_nextUtenteId, nome, email);
      _nextUtenteId++;
      _numUtentes++;
      _utentes.add(utente);
      return _nextUtenteId - 1;
    }
  }


  /**
   * Obtém Utente pedido
   * 
   * @param id Id único ao Utente pedido
   * @return instância de Utente pedido
   * @throws UserNotFoundException se o Id não estiver associado a algum Utente
   */
  public Utente getUtente (int id) throws UserNotFoundException {
    if (id >= _nextUtenteId) {
      throw new UserNotFoundException(id);
    }
    for (Utente utente : _utentes) {
      if (utente.getId() == id) {
        return utente;
      }
    }
    throw new UserNotFoundException(id);
  }

  /**
   * Obtém Lista completa de Utentes inscritos na Biblioteca
   * 
   * @return Lista completa de Utentes
   */
  public List<Utente> getUtentes () {
    List<Utente> ret = new ArrayList<>();
    for (Utente utente : _utentes) {
      ret.add(utente);
    }
    return ret;
  }

  /**
   * Obtém uma String com informações acerca de cada Utente inscrito na Biblioteca
   * 
   * @return String com informações dos Utentes
   */
  public String getAllUtenteString () {
    String ret = new String();
    int cnt = 0;
    for (Utente utente : _utentes) {
      ret += utente.utenteString();
      cnt++;
      if (cnt < _numUtentes) {
        ret += "\n";
      }
    }
    return ret;
  }

  /**
   * Adiciona um Livro á Biblioteca e aos respetivos Criadores
   * 
   * @param title título do Livro
   * @param price preço do Livro
   * @param categoria categoria do Livro
   * @param copies número de cópias do Livro
   * @param isbn isbn do Livro 
   * @param criadores Lista de Criadores do Livro
   * @return instância de Livro criado
   */
  public Obra addLivro(String title , int price, Categoria categoria, int copies, String isbn, List<Criador> criadores ){
    Obra obra = new Livro(_nextObraId, title, price, categoria, copies, isbn, criadores);
    _obras.put(_nextObraId, obra);
    for (Criador criador: criadores){
      _criadores.get(criador.getNome()).add(obra);
    }
    _nextObraId ++;
    _numObras ++;
    return obra;
  }

  /**
   * Adiciona um Dvd á Biblioteca e ao Criador(realizador)
   * 
   * @param title título do Dvd
   * @param price preço do Dvd
   * @param categoria categoria do Dvd
   * @param copies número de cópias do Dvd
   * @param igac igac do Dvd
   * @param realizador Criador do Dvd
   * @return instância de Dvd criado
   */
  public Obra addDvd(String title , int price, Categoria categoria, int copies, String igac, Criador realizador ){
    Obra obra = new Dvd(_nextObraId, title, price, categoria, copies, igac, realizador);
    _obras.put(_nextObraId, obra);
    _criadores.get(realizador.getNome()).add(obra);
    _nextObraId ++;
    _numObras ++;
    return obra;
  }

  /**
   * Obtém Obra pedida pelo Id único
   * 
   * @param id Id respetivo á Obra
   * @return instância de Obra pedida
   * @throws WorkNotFoundException se Id não estiver associado a alguma Obra
   */
  public Obra getObra (int id) throws WorkNotFoundException{
    if (!(_obras.containsKey(id))) throw new WorkNotFoundException(id); 
    return _obras.get(id); 

  }

  /**
   * Obtém a descrição para cada Obra na Biblioteca
   * 
   * @return String com as informações de cada Obra
   */
  public String getAllObrasString(){
    String ret = new String();
    int cont = 0;
    for(Obra obra: _obras.values()){
      ret += obra.getDescription();
      cont++;
      if (cont < _numObras)
        ret += "\n";
    }
    return ret;
  }
  
  /**
   * Obtém todas a Obras de um Criador
   * 
   * @param nome nome do Criador
   * @return unmodifiable Set das Obras do Criador 
   * @throws CreatorNotFoundException se não existir um Criador com o nome dado
   */
  public Set<Obra> getObrasCriador (String nome) throws CreatorNotFoundException{
    if (_criadores.get(nome) == null ) throw new CreatorNotFoundException(nome);
    return Collections.unmodifiableSet(_criadores.get(nome).obras());
  }

  /**
   * Obtém as informações completas das Obras de um Criador
   * 
   * @param nome nome do Criador
   * @return String com as informações das Obras do Criador
   * @throws CreatorNotFoundException se não existir um Criador com o nome dado
   */
  public String getObrasCriadorString (String nome) throws CreatorNotFoundException{
    String ret = new String();
    Set<Obra> list = getObrasCriador(nome);
    int cont = 0;
    for(Obra obra: list){
      cont++;
      ret += obra.getDescription();
      if(cont < list.size())
        ret += "\n";
    }
    return ret;
  }

  /**
   * Adiciona um Criador á Biblioteca
   * 
   * @param nome nome do Criador
   * @return instância do Criador criado
   */
  public Criador addCriador(String nome){
    if( _criadores.containsKey(nome) ) return _criadores.get(nome);
    Criador criador = new Criador(nome);
    _criadores.put(nome, criador);
    return criador;
  }

  public void pagarMulta (int id) throws UserNotFoundException, UserActivityException {
    Utente utente = getUtente(id);
    if (utente.getAtividade() == true) {
      throw new UserActivityException(id);
    } else {
      utente.limpaMulta();
      if (utente.getNumForaPrazo() == 0) {
        utente.setAtividade(true);
      }
    }
  }

  public String pesquisaTermoObras(String termo){
    String ret = new String();
    boolean linha = false;
    for(Obra obras: _obras.values()){
      if(obras.searchSpecificObras(termo) == true){
        if(linha == true){  
          ret += "\n";
          ret += obras.getDescription();
        }
        if(linha == false){
          ret += obras.getDescription();
          linha = true;
        }
      }
    }
    return ret;
  }

  public void removeCriador(List<Criador> list){
    for (Criador criador: list){
      _criadores.remove(criador.getNome());
    }
  }

  public boolean changeCopiesSuper(Obra obra, int copies){
    if (obra.getDisponiveis() + copies < 0)
      return false;
    if (obra.getDisponiveis() + copies == 0){
      List<Criador> list = new ArrayList<>();
      for (Criador criador: _criadores.values()){
        if(criador.obras().contains(obra)){
          criador.remove(obra);
          if(criador.obras().size() == 0)
            list.add(criador);
        }
      }
      removeCriador(list);
      return true;
    }
    obra.changeCopies(copies);
    return true;

  }

  public int requisitaObra (int utenteId, int obraId) throws UserNotFoundException, WorkNotFoundException/*, RuleNotPassedException, WorkNotAvailableException*/ {
    Utente utente = getUtente(utenteId);
    Obra obra = getObra(obraId);
    for (Regras regra : _regras) {
      if (regra.verificar(utente, obra) == false) {
        return regra.getId();
        /*if (regra.getId() != 3) {
          throw new RuleNotPassedException(regra.getId());
        } else {
          throw new WorkNotAvailableException();
        }*/
      }
    }
    int deadline = utente.getTipo().prazo(obra) + _dia.getDia();
    utente.addRequis(obra, deadline);
    obra.changeDisponiveis(-1);
    return 0;
  }

  public void devolveObra (int utenteId, int obraId) throws UserNotFoundException, WorkNotFoundException, RequisNotFoundException {
    Utente utente = getUtente(utenteId);
    Obra obra = getObra(obraId);
    if (utente.hasRequis(obraId) == false) {
      throw new RequisNotFoundException(utenteId, obraId);
    }
    int today = _dia.getDia();
    int deadline = utente.getRequis(obraId).getDeadline();
    utente.removeRequis(obraId);
    obra.changeDisponiveis(1);
    if (today > deadline) {
      utente.setCredit(0);
    } else {
      utente.setCredit(utente.getCredit() + 1);
    }
    utente.updateTipo();
  }

  public void updateEstadoUtentes () {
    for (Utente utente : _utentes) {
      utente.updateEstado(this);
    }
  }

  /**
   * Read text input file at the beginning of the program and populates the
   * the state of this library with the domain entities represented in the text file.
   * 
   * @param filename name of the text input file to process
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO error while processing the text file
   **/
  void importFile(String filename) throws UnrecognizedEntryException, IOException {
    _parser.parseFile(filename);
    _isModified = true;
  }
}
