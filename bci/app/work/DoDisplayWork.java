package bci.app.work;

import bci.core.LibraryManager;
import bci.app.exception.NoSuchWorkException;
import bci.core.exception.WorkNotFoundException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command to display a work.
 */
class DoDisplayWork extends Command<LibraryManager> {

  DoDisplayWork(LibraryManager receiver) {
    super(Label.SHOW_WORK, receiver);
    //FIXME add command fields
  }

  @Override
  protected final void execute() throws CommandException {
     //FIXME implement command
     int id = Form.requestInteger(Prompt.workId());
     try {
      _display.addLine(_receiver.getObra(id).getDescription());
      _display.display();
     } catch (WorkNotFoundException wnfe) {
      throw new NoSuchWorkException(id);
     }
  }
}
