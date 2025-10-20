package bci.app.request;

import bci.core.LibraryManager;
import bci.app.exception.NoSuchUserException;
import bci.app.exception.NoSuchWorkException;
import bci.app.exception.BorrowingRuleFailedException;
import bci.core.exception.RuleNotPassedException;
import bci.core.exception.UserNotFoundException;
import bci.core.exception.WorkNotAvailableException;
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
    addIntegerField("userId", bci.app.user.Prompt.userId());
    addIntegerField("workId", bci.app.work.Prompt.workId());
  }

  @Override
  protected final void execute() throws CommandException {
    int userId = integerField("userId");
    int workId = integerField("workId");
    try {
        _receiver.requisitaObra(userId, workId);
        _receiver.setIsModified(true);
        int prazo = _receiver.getUtente(userId).getTipo().prazo(_receiver.getObra(workId));
        int deadline = _receiver.getData().getDia() + prazo;
        _display.addLine(Message.workReturnDay(workId, deadline));
        _display.display();
    } catch (UserNotFoundException unfe) {
      throw new NoSuchUserException(userId);
    } catch (WorkNotFoundException wnfe) {
      throw new NoSuchWorkException(workId);
    } catch (RuleNotPassedException rnpe) {
      throw new BorrowingRuleFailedException(userId, workId, rnpe.getId());
    } catch (WorkNotAvailableException wnae) {
      try {
        boolean bool = Form.confirm(Prompt.returnNotificationPreference());
        if (bool == true) {
          _receiver.getObra(workId).addNotifDisp(userId, _receiver.getLibrary());
          _receiver.setIsModified(true);
        }
      } catch (UserNotFoundException unfe) {
        throw new NoSuchUserException(userId);
      } catch (WorkNotFoundException wnfe) {
        throw new NoSuchWorkException(workId); 
      }
    }
  }
}
