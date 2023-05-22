package us.obviously.itmo.prog.client.commands;

import us.obviously.itmo.prog.client.manager.Management;

import java.util.HashMap;

public class LoginCommand extends AbstractCommand{
    public LoginCommand(Management manager){
        super(manager, "login", "Войти в аккаунт");
        addParameter("login_name", "Имя аккаунта");
        addParameter("login_password", "Пароль от аккаунта");
    }

    @Override
    public void execute(HashMap<String, String> args) {

    }
}
