package bci.app.main;

import bci.core.LibraryManager;
import bci.app.exception.FileOpenFailedException;
import bci.core.exception.UnavailableFileException;
import java.io.IOException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

class DoOpenFile extends Command<LibraryManager> {

  DoOpenFile(LibraryManager receiver) {
    super(Label.OPEN_FILE, receiver);
    
  }

  @Override
  protected final void execute() throws CommandException {
    if (_receiver.getIsModified() == true) {
      boolean bool = Form.confirm(Prompt.saveBeforeExit());
      if (bool == true) {
        DoSaveFile dsp = new DoSaveFile(_receiver);
        dsp.execute();
      }
    }
    String filename = Form.requestString(Prompt.openFile());
    try {
    //FIXME implement command
      _receiver.load(filename);
    } catch (IOException ioe) {
      throw new FileOpenFailedException(ioe);
    } catch (ClassNotFoundException cnfe) {
      throw new FileOpenFailedException(cnfe);
    } catch (UnavailableFileException ufe) {
      throw new FileOpenFailedException(ufe);
    }
  
  }
}