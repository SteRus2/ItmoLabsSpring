package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.UserInfo;
import us.obviously.itmo.prog.common.action_models.UserModel;
import us.obviously.itmo.prog.common.data.LocalDataCollection;
import us.obviously.itmo.prog.server.exceptions.UserDoesNotExistException;
import us.obviously.itmo.prog.server.exceptions.WrongPasswordException;

public class LoginAction extends Action<UserModel, UserInfo> {
    public LoginAction() {
        super("login");
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, UserModel arguments) {
        try {
            UserInfo result = getDatabaseManager().signIn(arguments);
            return new Response(this.getResponse().serialize(result), ResponseStatus.OK);
        } catch (UserDoesNotExistException | WrongPasswordException e) {
            return new Response("Ошибка во время авторизации пользователя: " + e.getMessage(), ResponseStatus.UNAUTHORIZED);
        }
    }
}
