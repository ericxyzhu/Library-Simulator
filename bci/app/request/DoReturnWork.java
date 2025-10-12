package bci.app.request;

import bci.core.LibraryManager;
import bci.core.exception.UserNotFoundException;
import bci.core.exception.WorkNotFoundException;
import bci.app.exception.BorrowingRuleFailedException;
import bci.app.exception.NoSuchUserException;
import bci.app.exception.NoSuchWorkException;
import bci.app.exception.WorkNotBorrowedByUserException;
import bci.core.exception.RequisNotFoundException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * 4.4.2. Return a work.
 */
class DoReturnWork extends Command<LibraryManager> {

  DoReturnWork(LibraryManager receiver) {
    super(Label.RETURN_WORK, receiver);
    addIntegerField("userId", "Introduza o número de utente: ");
    addIntegerField("workId", "Introduza o número da obra: ");
  }

  @Override
  protected final void execute() throws CommandException {
    int userId = integerField("userid");
    int workId = integerField("workId");
    try {
      _receiver.devolveObra(userId, workId);
      int today = _receiver.getData().getDia();
      int deadline = _receiver.getUtente(userId).getRequis(workId).getDeadline();
      if (today - deadline > 0) {
        _receiver.getUtente(userId).changeNumForaPrazo(-1);
        int multa = (today - deadline) * 5;
        _display.addLine(Message.showFine(userId, multa));
        _display.display();
        boolean bool = Form.confirm(Prompt.finePaymentChoice());
        if (bool == true) {
          if (_receiver.getUtente(userId).getNumForaPrazo() == 0 && _receiver.getUtente(userId).getMulta() == 0) {
            _receiver.getUtente(userId).setAtividade(true);
          }
        } else {
          _receiver.getUtente(userId).addMulta(multa);
        }
      }
    } catch (UserNotFoundException unfe) {
      throw new NoSuchUserException(userId);
    } catch (WorkNotFoundException wnfe) {
      throw new NoSuchWorkException(workId);
    } catch (RequisNotFoundException rnfe) {
      throw new WorkNotBorrowedByUserException(workId, userId);
    }
  }
}
