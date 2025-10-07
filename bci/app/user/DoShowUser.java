package bci.app.user;

import bci.core.LibraryManager;
import bci.app.exception.NoSuchUserException;
import bci.core.exception.*;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * 4.2.2. Show specific user.
 */
class DoShowUser extends Command<LibraryManager> {

  DoShowUser(LibraryManager receiver) {
    super(Label.SHOW_USER, receiver);
    //FIXME add command fields
  }

  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
    int id = Form.requestInteger(Prompt.userId());
    try {
      _display.addLine(_receiver.getUtente(id).utenteString());
      _display.display();
    } catch (UserNotFoundException unfe) {
      throw new NoSuchUserException(id);
    }
    
  }
}
