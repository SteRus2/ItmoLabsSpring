package us.obviously.itmo.prog.common.serializers;

import us.obviously.itmo.prog.client.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.server.exceptions.CantParseDataException;
import us.obviously.itmo.prog.server.exceptions.FailedToDumpsEx;
import us.obviously.itmo.prog.server.exceptions.IncorrectValuesTypeException;

import java.io.*;

public final class Serializer<T> {
    public byte[] serialize(T object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            oos.close();
        } catch (IOException e) {
        }
        return baos.toByteArray();
    }

    public T parse(byte[] body) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(body));
        T readStrObj = (T) ois.readObject();
        ois.close();
        return readStrObj;
    }
}
