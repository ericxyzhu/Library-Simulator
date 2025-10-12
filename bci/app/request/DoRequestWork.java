package bci.app.request;

import bci.core.LibraryManager;
import bci.app.exception.NoSuchUserException;
import bci.app.exception.NoSuchWorkException;
import bci.app.exception.BorrowingRuleFailedException;
import bci.core.exception.RuleNotPassedException;
import bci.core.exception.UserNotFoundException;
import bci.core.exception.WorkNotFoundException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

//FIXME add more imports if needed

/**
 * 4.4.1. Request work.
 */
class DoRequestWork extends Command<LibraryManager> {

  DoRequestWork(LibraryManager receiver) {
    super(Label.REQUEST_WORK, receiver);
    addIntegerField("userId", "Introduza o número de utente: ");
    addIntegerField("workId", "Introduza o número da obra: ");
  }

  @Override
  protected final void execute() throws CommandException {
    int userId = integerField("userid");
    int workId = integerField("workId");
    try {
        int errorCode = _receiver.requisitaObra(userId, workId);
        if (errorCode == 3) {
          boolean bool = Form.confirm(Prompt.returnNotificationPreference());
          if (bool == true) {
            _receiver.getObra(workId).addNotifDisp(userId, _receiver.getLibrary());
          }
          return;
        }
        if (errorCode >= 1) {
          throw new BorrowingRuleFailedException(userId, workId, errorCode);
        }
        _receiver.getObra(workId).removeNotifDisp(userId);
        int prazo = _receiver.getUtente(userId).getTipo().prazo(_receiver.getObra(workId));
        int deadline = _receiver.getData().getDia() + prazo;
        _display.addLine(Message.workReturnDay(workId, deadline));
        _display.display();
    } catch (UserNotFoundException unfe) {
      throw new NoSuchUserException(userId);
    } catch (WorkNotFoundException wnfe) {
      throw new NoSuchWorkException(workId);
    }
  }
}
