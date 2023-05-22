package us.obviously.itmo.prog.client.commands;

import us.obviously.itmo.prog.client.manager.Management;

import java.util.HashMap;

public class RegisterCommand extends AbstractCommand{

    public RegisterCommand(Management manager) {
        super(manager, "register", "Зарегистрироваться в системе");
        addParameter("register_name", "Имя аккаунта");
        addParameter("register_password", "Пароль от аккаунта");
    }

    @Override
    public void execute(HashMap<String, String> args) {

    }
}
