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
  private Parser _parser = new Parser(this);
  private String _filename;
  private Map<Integer, Obra> _obras;
  private Set<Utente> _utentes;
  private Map<String, Criador> _criadores;
  
  public Library () {
    _obras = new TreeMap<>();
    _utentes = new TreeSet<>();
    _criadores = new TreeMap<>();
  }
  
  public Dia getData () {
    return new Dia(_dia.getDia());
  }

  public void avancaData (int n) {
    _dia.avanca(n);
  }

  protected void setFilename (String filename) {
    _filename = filename;
  }

  public String getFilename () {
    return _filename;
  }

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

  public List<Utente> getUtentes () {
    List<Utente> ret = new ArrayList<>();
    for (Utente utente : _utentes) {
      ret.add(utente);
    }
    return ret;
  }

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

  public Obra addLivro(String title , int price, Categoria categoria, int copies, String isbn, List<Criador> criadores ){
    Obra obra = new Livro(_nextObraId, title, price, categoria, copies, isbn, criadores);
    _obras.put(_nextObraId, obra);
    for (Criador criador: criadores){
      _criadores.get(criador.getnome()).add(obra);
    }
    _nextObraId ++;
    _numObras ++;
    return obra;
  }

  public Obra addDvd(String title , int price, Categoria categoria, int copies, String igac, Criador realizador ){
    Obra obra = new Dvd(_nextObraId, title, price, categoria, copies, igac, realizador);
    _obras.put(_nextObraId, obra);
    _criadores.get(realizador.getnome()).add(obra);
    _nextObraId ++;
    _numObras ++;
    return obra;
  }


  public Obra getObra (int id) throws WorkNotFoundException{
    if (!(_obras.containsKey(id))) throw new WorkNotFoundException(id); 
    return _obras.get(id); 

  }

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
  
  public Set<Obra> getObrasCriador (String nome) throws CreatorNotFoundException{
    if (_criadores.get(nome) == null ) throw new CreatorNotFoundException(nome);
    return Collections.unmodifiableSet(_criadores.get(nome).obras());
  }

  public String getObrasCriadorString (String nome) throws CreatorNotFoundException{
    String ret = new String();
    Set<Obra> list = new TreeSet<>();
    int cont = 0;
    list = getObrasCriador(nome);
    for(Obra obra: list){
      cont++;
      ret += obra.getDescription();
      if(cont < list.size())
        ret += "\n";
    }
    return ret;
  }

  public Criador addCriador(String nome){
    if( _criadores.containsKey(nome) ) return _criadores.get(nome);
    Criador criador = new Criador(nome);
    _criadores.put(nome, criador);
    return criador;
  }

  /**
   * Read text input file at the beginning of the program and populates the
   * the state of this library with the domain entities represented in the text file.
   * 
   * @param filename name of the text input file to process
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO error while processing the text file
   **/
  void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */  {
    //FIXME implement method
    _parser.parseFile(filename);
  }
}
