package bci.app.work;

import bci.core.LibraryManager;
import pt.tecnico.uilib.menus.Command;

/**
 * Perform search according to miscellaneous criteria.
 */
class DoPerformSearch extends Command<LibraryManager> {

  DoPerformSearch(LibraryManager receiver) {
    super(Label.PERFORM_SEARCH, receiver);
    addStringField("termo", Prompt.searchTerm());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  protected final void execute() {
    String termo = stringField("termo");
    _display.addLine(_receiver.pesquisaTermoObras(termo));
    _display.display();
  }
}
