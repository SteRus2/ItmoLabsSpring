package us.obviously.itmo.prog.common.action_models;

import java.io.Serializable;

public class UserModel extends Model implements Serializable {

    private final String username;
    private final String password;

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
