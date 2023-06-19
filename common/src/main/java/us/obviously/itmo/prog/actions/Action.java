package us.obviously.itmo.prog.actions;

import us.obviously.itmo.prog.UserInfo;
import us.obviously.itmo.prog.server.data.LocalDataCollection;
import us.obviously.itmo.prog.server.database.DatabaseManager;
import us.obviously.itmo.prog.server.net.JwtGenerator;
import us.obviously.itmo.prog.serializers.Serializer;

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

    public Response run(LocalDataCollection dataCollection, byte[] arguments, UserInfo userInfo, DatabaseManager databaseManager) throws IOException, ClassNotFoundException {
        this.databaseManager = databaseManager;
        this.userInfo = userInfo;
        T args = this.request.parse(arguments);
        return this.execute(dataCollection, args);
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
