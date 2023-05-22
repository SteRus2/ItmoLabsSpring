package us.obviously.itmo.prog.common;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private String login;
    private String password;

    public UserInfo(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
