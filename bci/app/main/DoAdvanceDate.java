package bci.app.main;

import bci.core.LibraryManager;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
//FIXME add more imports if needed

/**
 * 4.1.3. Advance the current date.
 */
class DoAdvanceDate extends Command<LibraryManager> {

  DoAdvanceDate(LibraryManager receiver) {
    super(Label.ADVANCE_DATE, receiver);
  }

  @Override
  protected final void execute() {
    _receiver.setIsModified(true);
    Integer num = Form.requestInteger(Prompt.daysToAdvance());
    _receiver.avancaData(num);
  }
}
