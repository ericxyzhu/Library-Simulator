package bci.app.main;

import bci.app.exception.FileOpenFailedException;
import bci.core.LibraryManager;
import bci.core.exception.MissingFileAssociationException;
import java.io.FileNotFoundException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
// FIXME add more imports if needed
import pt.tecnico.uilib.menus.CommandException;

import java.io.IOException;

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<LibraryManager> {

  DoSaveFile(LibraryManager receiver) {
    super(Label.SAVE_FILE, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    if (_receiver.getHasFilename() == false){
      addStringField("filename", Prompt.newSaveAs());
    }
    try {
      if (_receiver.getHasFilename() == true){
        _receiver.save();
      } else {
        String filename = stringField("filename");
        _receiver.saveAs(filename);
      }
    } catch (MissingFileAssociationException mfae) {
      throw new FileOpenFailedException(mfae);
    } catch (FileNotFoundException fnfe) {
      throw new FileOpenFailedException(fnfe);
    } catch (IOException ioe) {
      throw new FileOpenFailedException(ioe);
    }
    // FIXME implement command and create a local Form
    
    
  }
}
