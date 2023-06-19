package us.obviously.itmo.prog.commands;

import us.obviously.itmo.prog.console.Messages;
import us.obviously.itmo.prog.manager.Management;
import us.obviously.itmo.prog.action_models.UserModel;
import us.obviously.itmo.prog.server.exceptions.BadRequestException;

import java.util.HashMap;

public class LoginCommand extends AbstractCommand {
    public LoginCommand(Management manager) {
        super(manager, "login", "Войти в аккаунт");
        addParameter("login_name", "Имя аккаунта");
        addParameter("login_password", "Пароль от аккаунта");
    }

    @Override
    public void execute(HashMap<String, String> args) {
        var user = new UserModel(args.get("login_name"), args.get("login_password"));
        try {
            var answer = manager.getDataCollection().loginUser(user);
            Messages.printStatement("~grАвторизация прошла успешно! Привет, ~ye" + answer.getLogin() + "~gr!~=");
        } catch (BadRequestException e) {
            Messages.printStatement("~reОшибка запроса: " + e.getMessage() + "~=");
        }
    }
}
