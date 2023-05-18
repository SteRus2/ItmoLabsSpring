package us.obviously.itmo.prog.common.actions;

import java.io.Serializable;

public class Request implements Serializable {
    private String command;
    private byte[] body;
    public Request(String command, byte[] body) {
        this.command = command;
        this.body = body;
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
                ", body='" + body.toString() + '\'' +
                '}';
    }
}
