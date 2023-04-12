package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.serializers.Serializer;
import us.obviously.itmo.prog.server.exceptions.*;

public abstract class Action<T, D> {
    private final Serializer<T> request;
    private final Serializer<D> response;
    private final String name;
    private final DataCollection dataCollection;

    public Action(DataCollection dataCollection, String name, Serializer<T> request, Serializer<D> response) {
        this.dataCollection = dataCollection;
        this.name = name;
        this.request = request;
        this.response = response;
    }

    public abstract D execute(T arguments) throws UsedKeyException, NoSuchIdException, FileNotWritableException, FailedToDumpsEx, CantWriteDataException;

    public DataCollection getDataCollection() {
        return dataCollection;
    }

    public Serializer<D> getResponse() {
        return response;
    }

    public Serializer<T> getRequest() {
        return request;
    }

    public String getName() {
        return name;
    }
}
