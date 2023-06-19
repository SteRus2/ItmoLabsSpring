package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.UserInfo;
import us.obviously.itmo.prog.common.action_models.UserModel;
import us.obviously.itmo.prog.common.server.data.LocalDataCollection;
import us.obviously.itmo.prog.common.server.exceptions.UserDoesNotExistException;
import us.obviously.itmo.prog.common.server.exceptions.WrongPasswordException;

import java.util.HashMap;
import java.util.Map;

public class LoginAction extends Action<UserModel, String> {
    public LoginAction() {
        super("login");
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, UserModel arguments) {
        try {
            UserInfo result = getDatabaseManager().signIn(arguments);

            Map<String, String> claims = new HashMap<>();

            claims.put("username", result.getLogin());
            claims.put("id", String.valueOf(result.getId()));
            claims.put("aud", "*");

            var token = jwtGenerator.generateJwt(claims);

            return new Response(this.getResponse().serialize(token), ResponseStatus.OK);
        } catch (UserDoesNotExistException | WrongPasswordException e) {
            return new Response("Ошибка во время авторизации пользователя: " + e.getMessage(), ResponseStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new Response("Ошибка во время создания токена: " + e.getMessage(), ResponseStatus.UNAUTHORIZED);
        }
    }
}
