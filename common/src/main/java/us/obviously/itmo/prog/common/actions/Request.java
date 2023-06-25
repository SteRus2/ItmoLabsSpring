package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.UserInfo;
import us.obviously.itmo.prog.common.UserInfoExplicit;

import java.io.Serializable;

public class Request implements Serializable {
    private static int lastId = 0;
    private final String command;
    private final byte[] body;
    private final String authToken;
    private final int id;
    /**
     * @deprecated
     */
    private UserInfoExplicit userInfo;

    public Request(String command, byte[] body) {
        this.command = command;
        this.body = body;
        this.authToken = null;
        this.id = lastId++;
    }

    public Request(String command, byte[] body, String authToken) {
        this.command = command;
        this.body = body;
        this.authToken = authToken;
        this.id = lastId++;
    }

    /**
     * @deprecated
     */
    public UserInfo getUserInfo() {
        return null;
    }

    public String getAuthToken() {
        return authToken;
    }

    /**
     * @deprecated
     */
    public String getLogin() {
        return null;
    }

    /**
     * @deprecated
     */
    public String getPassword() {
        return null;
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
                ", body=" + body.toString() +
                '}';
    }

    public Integer getId() {
        return id;
    }
}
