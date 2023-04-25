package us.obviously.itmo.prog.common.actions;

import java.io.Serializable;

public class Request implements Serializable {
    String command;
    String body;
    public Request(String command, String body) {
        this.command = command;
        this.body = body;
    }
}
