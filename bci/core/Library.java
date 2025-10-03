package bci.core;

import bci.core.exception.UnrecognizedEntryException;
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
  
  public Library () {
    _obras = new TreeMap<>();
    _utentes = new TreeSet<>();
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

  public int registaUtente (String nome, String email) {
    Utente utente = new Utente(_nextUtenteId, nome, email);
    _nextUtenteId++;
    _numUtentes++;
    _utentes.add(utente);
    return _nextUtenteId - 1;
  }

  public Utente getUtente (int id) {
    for (Utente utente : _utentes) {
      if (utente.getId() == id) {
        return utente;
      }
    }
    return null;
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
