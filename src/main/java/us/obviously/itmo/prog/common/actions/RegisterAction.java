package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.UserInfo;
import us.obviously.itmo.prog.common.action_models.UserModel;
import us.obviously.itmo.prog.common.data.LocalDataCollection;
import us.obviously.itmo.prog.server.exceptions.FailedToRegisterUserException;

public class RegisterAction extends Action<UserModel, UserInfo> {
    public RegisterAction() {
        super("register");
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, UserModel arguments) {
        try {
            UserInfo result = getDatabaseManager().registerUser(arguments);
            return new Response(this.getResponse().serialize(result), ResponseStatus.OK);
        } catch (FailedToRegisterUserException e) {
            return new Response("Ошибка во время регистрации пользователя: " + e.getMessage(), ResponseStatus.UNAUTHORIZED);
        }
    }
}
