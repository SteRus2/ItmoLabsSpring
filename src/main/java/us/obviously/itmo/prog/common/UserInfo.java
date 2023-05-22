package us.obviously.itmo.prog.common;

public class UserInfo {
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
