package us.obviously.itmo.prog.actions;

import us.obviously.itmo.prog.UserInfo;
import us.obviously.itmo.prog.action_models.UserModel;
import us.obviously.itmo.prog.server.data.LocalDataCollection;
import us.obviously.itmo.prog.server.exceptions.FailedToRegisterUserException;

import java.util.HashMap;
import java.util.Map;

public class RegisterAction extends Action<UserModel, String> {
    public RegisterAction() {
        super("register");
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, UserModel arguments) {
        try {
            UserInfo result = getDatabaseManager().registerUser(arguments);

            Map<String, String> claims = new HashMap<>();

            claims.put("username", result.getLogin());
            claims.put("id", String.valueOf(result.getId()));
            claims.put("aud", "*");

            var token = jwtGenerator.generateJwt(claims);

            return new Response(this.getResponse().serialize(token), ResponseStatus.OK);
        } catch (FailedToRegisterUserException e) {
            return new Response("Ошибка во время регистрации пользователя: " + e.getMessage(), ResponseStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new Response("Ошибка во время создания токена: " + e.getMessage(), ResponseStatus.UNAUTHORIZED);
        }
    }
}
