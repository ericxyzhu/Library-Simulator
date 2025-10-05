package bci.core;

import bci.core.exception.*;
import java.io.*;
import java.util.*;
import java.util.zip.DeflaterOutputStream;

// FIXME import classes

/**
 * The façade class. Represents the manager of this application. It manages the current
 * library and works as the interface between the core and user interaction layers.
 */
public class LibraryManager {


  /** The object doing all the actual work. */
  /* The current library */
  // FIXME: initialize this field
  private Library _library;

  // FIXME: add more fields if needed
  public LibraryManager () {
    _library = new Library();
  }

  public Dia getData () {
    return _library.getData();
  }

  public void avancaData (int n) {
    _library.avancaData(n);
  }

  public String getFilename () {
    return _library.getFilename();
  }

  public int registaUtente (String nome, String email) throws EmptyNameException {
    return _library.registaUtente(nome, email);
  }

  public Utente getUtente (int id) throws UserNotFoundException {
    return _library.getUtente(id);
  }

  public List<Utente> getUtentes () {
    return _library.getUtentes();
  }

  public String getAllUtenteString () {
    return _library.getAllUtenteString();
  }

  public Obra addLivro(String title , int price, Categoria categoria, int copies, String isbn, List<Criador> criadores ){
    return _library.addLivro(title, price, categoria, copies, isbn, criadores);
  }

  public Obra addDvd(String title , int price, Categoria categoria, int copies, String igac, Criador realizador ){
    return _library.addDvd(title, price, categoria, copies, igac, realizador);
  }

  public Obra getObra (int id) throws WorkNotFoundException{
    return _library.getObra(id);
  }

  public String getAllObrasString(){
    return _library.getAllObrasString();
  }
  
  public Set<Obra> getObrasCriador (String nome) throws CreatorNotFoundException{
    return _library.getObrasCriador(nome);
  }

  public String getObrasCriadorString (String nome) throws CreatorNotFoundException{
    return _library.getObrasCriadorString(nome);
  }
  public Criador addCriador(String nome){
    return _library.addCriador(nome);
  }
  /**
   * Saves the serialized application's state into the file associated to the current library
   *
   * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
   * @throws MissingFileAssociationException if the current library does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   **/
  public void save() throws MissingFileAssociationException, FileNotFoundException, IOException {
    // FIXME implement serialization method
    if (_library.getFilename() == null) {
      throw new MissingFileAssociationException();
    } else {
      saveAs(_library.getFilename());
    }
  }

  /**
   * Saves the serialized application's state into the specified file. The current library is
   * associated to this file.
   *
   * @param filename the name of the file.
   * @throws FileNotFoundException if for some reason the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current library does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   **/
  public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
    // FIXME implement serialization method
    _library.setFilename(filename);
    ObjectOutputStream obOut = null;
    try {
      FileOutputStream fpout = new FileOutputStream(filename);
      DeflaterOutputStream dOut = new DeflaterOutputStream(fpout);
      obOut = new ObjectOutputStream(dOut);
      obOut.writeObject(_library);
    } finally {
      if (obOut != null)
      obOut.close();
    }
  }

  /**
   * Loads the previously serialized application's state as set it as the current library.
   *
   * @param filename name of the file containing the serialized application's state
   *        to load.
   * @throws UnavailableFileException if the specified file does not exist or there is
   *         an error while processing this file.
   **/
  public void load(String filename) throws UnavailableFileException, IOException, ClassNotFoundException {
    // FIXME implement serialization method
    ObjectInputStream objIn = null;
    try {
      objIn = new ObjectInputStream(new FileInputStream(filename));
      _library = (Library)objIn.readObject();
    } catch (IOException ioe) {
      throw new UnavailableFileException(filename);
    } finally {
      if (objIn != null)
      objIn.close();
    }
  }

  /**
   * Read text input file and initializes the current library (which should be empty)
   * with the domain entities representeed in the import file.
   *
   * @param datafile name of the text input file
   * @throws ImportFileException if some error happens during the processing of the
   * import file.
   **/
  public void importFile(String datafile) throws ImportFileException {
    try {
      if (datafile != null && !datafile.isEmpty())
        _library.importFile(datafile);
    } catch (IOException | UnrecognizedEntryException /* FIXME maybe other exceptions */ e) {
      throw new ImportFileException(datafile, e);
    }
  } 
}
