package bci.app.work;

import bci.core.LibraryManager;
import bci.app.exception.NoSuchCreatorException;
import bci.core.exception.CreatorNotFoundException;
import pt.tecnico.uilib.menus.Command;
//FIXME add more imports if needed
import pt.tecnico.uilib.menus.CommandException;

/**
 * Display all works by a specific creator.
 */
class DoDisplayWorksByCreator extends Command<LibraryManager> {
  DoDisplayWorksByCreator(LibraryManager receiver) {
    super(Label.SHOW_WORKS_BY_CREATOR, receiver);
    //FIXME add command fields
    addStringField("nome", Prompt.creatorId());
  }

  @Override
  protected final void execute() throws CommandException {
    String nome = stringField("nome");
    try {
        _display.addLine(_receiver.getObrasCriadorString(nome));
        _display.display();
    } catch (CreatorNotFoundException cnfe) {
      throw new NoSuchCreatorException(nome);
    }
  }
}
