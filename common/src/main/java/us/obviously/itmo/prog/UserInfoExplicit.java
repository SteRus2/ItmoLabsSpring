package us.obviously.itmo.prog;

import java.io.Serializable;

public class UserInfoExplicit implements UserInfo, Serializable {
    private final Integer id;
    private final String login;
    private final String password;

    public UserInfoExplicit(Integer id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
