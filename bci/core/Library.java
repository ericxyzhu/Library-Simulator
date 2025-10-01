package bci.core;

import bci.core.exception.UnrecognizedEntryException;
import java.io.*;
// FIXME import classes

/**
 * Class that represents the library as a whole.
 */
public class Library implements Serializable {

  /** Serial number for serialization. */
  @Serial
  private static final long serialVersionUID = 202501101348L;

  private Dia _dia = new Dia();
  
  public Library () {
    
  }
  
  public Dia getData () {
    return new Dia(_dia.getDia());
  }

  public void avancaData (int n) {
    _dia.avanca(n);
  }

  /**
   * Read text input file at the beginning of the program and populates the
   * the state of this library with the domain entities represented in the text file.
   * 
   * @param filename name of the text input file to process
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   **/
  void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */  {
    //FIXME implement method
  }
}
