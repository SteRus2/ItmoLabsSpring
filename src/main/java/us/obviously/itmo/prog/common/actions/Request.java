package us.obviously.itmo.prog.common.actions;

import java.io.Serializable;

public class Request implements Serializable {
    private String command;
    private byte[] body;
    private String login;
    private String password;

    public Request(String command, byte[] body) {
        this.command = command;
        this.body = body;
    }

    public Request(String command, byte[] body, String login, String password) {
        this.command = command;
        this.body = body;
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
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
                ", login='" + login + '\'' +
                '}';
    }
}
