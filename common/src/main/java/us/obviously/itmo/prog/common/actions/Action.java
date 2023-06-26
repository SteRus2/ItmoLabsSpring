package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.UserInfo;
import us.obviously.itmo.prog.common.server.data.LocalDataCollection;
import us.obviously.itmo.prog.common.server.database.DatabaseManager;
import us.obviously.itmo.prog.common.server.net.JwtGenerator;
import us.obviously.itmo.prog.common.serializers.Serializer;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public abstract class Action<T, D> {
    static final JwtGenerator jwtGenerator;

    static {
        try {
            jwtGenerator = new JwtGenerator();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private final Serializer<T> request;
    private final Serializer<D> response;
    private final String name;
    private DatabaseManager databaseManager;
    private UserInfo userInfo;

    public Action(String name/*, Serializer<T> request, Serializer<D> response*/) {
        this.name = name;
        this.request = new Serializer<>();
        this.response = new Serializer<>();
    }

    public Response run(LocalDataCollection dataCollection, int requestId, byte[] arguments, UserInfo userInfo, DatabaseManager databaseManager) throws IOException, ClassNotFoundException {
        this.databaseManager = databaseManager;
        this.userInfo = userInfo;
        T args = this.request.parse(arguments);
        var response = this.execute(dataCollection, args);
        response.setRequestId(requestId);
        return response;
    }

    abstract public Response execute(LocalDataCollection dataCollection, T arguments);

    public Serializer<D> getResponse() {
        return response;
    }

    public Serializer<T> getRequest() {
        return request;
    }

    public String getName() {
        return name;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}