package us.obviously.itmo.prog.commands;

import us.obviously.itmo.prog.console.Messages;
import us.obviously.itmo.prog.manager.Management;
import us.obviously.itmo.prog.action_models.UserModel;
import us.obviously.itmo.prog.server.exceptions.BadRequestException;

import java.util.HashMap;

public class RegisterCommand extends AbstractCommand {

    public RegisterCommand(Management manager) {
        super(manager, "register", "Зарегистрироваться в системе");
        addParameter("register_name", "Имя аккаунта");
        addParameter("register_password", "Пароль от аккаунта");
    }

    @Override
    public void execute(HashMap<String, String> args) {
        var user = new UserModel(args.get("register_name"), args.get("register_password"));
        try {
            var answer = manager.getDataCollection().registerUser(user);
            Messages.printStatement("~grРегистрация прошла успешно! Добро пожаловать, ~ye" + answer.getLogin() + "~gr!~=");
        } catch (BadRequestException e) {
            Messages.printStatement("~reОшибка запроса: " + e.getMessage() + "~=");
        }
    }
}
