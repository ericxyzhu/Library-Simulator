package bci.app.work;

import bci.core.LibraryManager;
import bci.app.exception.NoSuchWorkException;
import bci.core.exception.WorkNotFoundException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command to display a work.
 */
class DoDisplayWork extends Command<LibraryManager> {

  DoDisplayWork(LibraryManager receiver) {
    super(Label.SHOW_WORK, receiver);
    addIntegerField("id", Prompt.workId());
  }

  @Override
  protected final void execute() throws CommandException {
     int id = integerField("id");
     try {
      _display.addLine(_receiver.getObra(id).getDescription());
      _display.display();
     } catch (WorkNotFoundException wnfe) {
      throw new NoSuchWorkException(id);
     }
  }
}
