package bci.app.user;

import bci.core.LibraryManager;
import bci.app.exception.UserRegistrationFailedException;
import bci.core.exception.EmptyNameException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * 4.2.1. Register new user.
 */
class DoRegisterUser extends Command<LibraryManager> {

  DoRegisterUser(LibraryManager receiver) {
    super(Label.REGISTER_USER, receiver);
    addStringField("nome", Prompt.userName());
    addStringField("email", Prompt.userEMail());
  }

  @Override
  protected final void execute() throws CommandException {
    String nome = stringField("nome");
    String email = stringField("email");
    try {
      int id = _receiver.registaUtente(nome, email);
      _display.addLine(Message.registrationSuccessful(id));
      _display.display();
      _receiver.setIsModified(true);
    } catch (EmptyNameException ene) {
      throw new UserRegistrationFailedException(nome, email);
    }
    
  }
}
