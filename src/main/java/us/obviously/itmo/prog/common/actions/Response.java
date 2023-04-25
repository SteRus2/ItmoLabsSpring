package us.obviously.itmo.prog.common.actions;

import java.io.Serializable;

public class Response implements Serializable {
    private final String body;
    private final ResponseStatus status;

    public Response(String body, ResponseStatus status) {
        this.body = body;
        this.status = status;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public String getBody() {
        return body;
    }
}
