package us.obviously.itmo.prog.client.commands;

import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.client.manager.Management;
import us.obviously.itmo.prog.common.action_models.UserModel;
import us.obviously.itmo.prog.common.exceptions.BadRequestException;

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
            Messages.printStatement("~re" + answer + "~=");
        } catch (BadRequestException e) {
            Messages.printStatement("~reОшибка запроса: " + e.getMessage() + "~=");
        }
    }
}
