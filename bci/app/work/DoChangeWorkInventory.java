package bci.app.work;

import bci.core.LibraryManager;
import bci.core.exception.WorkNotFoundException;
import bci.app.exception.NoSuchWorkException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Change the number of exemplars of a work.
 */
class DoChangeWorkInventory extends Command<LibraryManager> {
  DoChangeWorkInventory(LibraryManager receiver) {
    super(Label.CHANGE_WORK_INVENTORY, receiver);
     addIntegerField("id", Prompt.workId());
     addIntegerField("copies", Prompt.amountToDecrement());
  }

  @Override
  protected final void execute() throws CommandException {
    int id = integerField("id");
    int copies = integerField("copies");
    try{
      if((_receiver.changeCopies(id, copies) == false)) {
        _display.addLine(Message.notEnoughInventory(id, copies));
        _display.display();
      } else {
        _receiver.setIsModified(true);
      }
        
    } catch (WorkNotFoundException wnfe) {
      throw new NoSuchWorkException(id);
    }
    
    
  }
}
