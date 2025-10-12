package bci.app.main;

import bci.core.LibraryManager;
import pt.tecnico.uilib.menus.Command;

/**
 * 4.1.3. Advance the current date.
 */
class DoAdvanceDate extends Command<LibraryManager> {

  DoAdvanceDate(LibraryManager receiver) {
    super(Label.ADVANCE_DATE, receiver);
    addIntegerField("num", Prompt.daysToAdvance());
  }

  @Override
  protected final void execute() {
    _receiver.setIsModified(true);
    Integer num = integerField("num");
    _receiver.avancaData(num);
    _receiver.updateEstadoUtentes();
  }
}
