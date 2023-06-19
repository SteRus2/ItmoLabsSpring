package us.obviously.itmo.prog;

import java.io.Serializable;

public class UserInfoPublic implements UserInfo, Serializable {
    private final Integer id;
    private final String login;

    public UserInfoPublic(Integer id, String login) {
        this.id = id;
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
}
