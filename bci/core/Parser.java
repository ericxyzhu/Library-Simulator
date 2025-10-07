package bci.core;

import bci.core.exception.*;
import java.io.*;
import java.util.*;

class Parser implements Serializable {
  private Library _library;

  Parser(Library lib) {
    _library = lib;
  }

  void parseFile(String filename) throws IOException, UnrecognizedEntryException {
    String line;

    try (BufferedReader in = new BufferedReader(new FileReader(filename));) {
      while ((line = in.readLine()) != null)
        parseLine(line);
    }
  }
 
  private void parseLine(String line) throws UnrecognizedEntryException {
    String[] components = line.split(":");

    switch (components[0]) {
      case "USER":
        parseUser(components, line);
        break;

      case "DVD":
        parseDvd(components, line);
        break;

      case "BOOK":
        parseBook(components, line);
        break;

      default:
        throw new UnrecognizedEntryException("Tipo inválido " + components[0] + " na linha " + line);
    }
  }

  private void parseUser(String[] components, String line) throws UnrecognizedEntryException {
    try {
      if (components.length != 3)
        throw new UnrecognizedEntryException ("Número inválido de campos (3) na descrição de um utente: " + line);

      _library.registaUtente(components[1], components[2]);
    } catch (EmptyNameException ene) {
      throw new UnrecognizedEntryException("Nome ou Email vazio");
    }
  }

  private void parseDvd(String[] components, String line) throws UnrecognizedEntryException {
    if (components.length != 7)
      throw new UnrecognizedEntryException ("Número inválido de campos (7) na descrição de um DVD: " + line);

    int price = Integer.parseInt(components[3]);
    int nCopies = Integer.parseInt(components[6]);
    Categoria category = Categoria.valueOf(components[4]);
    Criador creator = _library.addCriador(components[2].trim());
    _library.addDvd(components[1], price, category, nCopies, components[5], creator);  
  }
  
  private void parseBook(String[] components, String line) throws UnrecognizedEntryException {
    if (components.length != 7)
      throw new UnrecognizedEntryException ("Número inválido de campos (7) na descrição de um Book: " + line);
    
    int price = Integer.parseInt(components[3]);
    int nCopies = Integer.parseInt(components[6]);
    Categoria category = Categoria.valueOf(components[4]);
    List<Criador> creators = new ArrayList<>();
    for (String name : components[2].split(","))
      creators.add(_library.addCriador(name.trim()));

    _library.addLivro(components[1], price, category, nCopies, components[5], creators); 
  }
}
