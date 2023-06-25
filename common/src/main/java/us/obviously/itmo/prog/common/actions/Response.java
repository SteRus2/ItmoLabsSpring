package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.serializers.Serializer;

import java.io.Serializable;

public class Response implements Serializable {
    private final byte[] body;
    private final ResponseStatus status;
    private int requestId;


    public Response(byte[] body, ResponseStatus status) {
        this.requestId = 0;
        this.body = body;
        this.status = status;
    }

    public Response(String body, ResponseStatus status) {
        this.requestId = 0;
        this.body = new Serializer<String>().serialize(body);
        this.status = status;
    }

    public Response(int requestId, byte[] body, ResponseStatus status) {
        this.requestId = requestId;
        this.body = body;
        this.status = status;
    }

    public Response(int requestId, String body, ResponseStatus status) {
        this.requestId = requestId;
        this.body = new Serializer<String>().serialize(body);
        this.status = status;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public byte[] getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Response{" +
                "body='" + body + '\'' +
                ", status=" + status +
                '}';
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }
}
