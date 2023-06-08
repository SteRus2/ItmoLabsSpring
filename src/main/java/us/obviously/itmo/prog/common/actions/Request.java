package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.UserInfo;
import us.obviously.itmo.prog.common.UserInfoExplicit;

import java.io.Serializable;
import java.util.Arrays;

public class Request implements Serializable {
    private final String command;
    private final byte[] body;
    private UserInfoExplicit userInfo;

    public Request(String command, byte[] body) {
        this.command = command;
        this.body = body;
    }

    public Request(String command, byte[] body, UserInfoExplicit userInfo) {
        this.command = command;
        this.body = body;
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public String getLogin() {
        return userInfo.getLogin();
    }

    public String getPassword() {
        return userInfo.getPassword();
    }

    public String getCommand() {
        return command;
    }

    public byte[] getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Request{" +
                "command='" + command + '\'' +
                ", body=" + Arrays.toString(body) +
                ", login='" + userInfo.getLogin() + '\'' +
                '}';
    }
}
