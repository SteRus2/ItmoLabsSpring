package us.obviously.itmo.prog.common.serializers;

import us.obviously.itmo.prog.client.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.server.exceptions.CantParseDataException;
import us.obviously.itmo.prog.server.exceptions.FailedToDumpsEx;
import us.obviously.itmo.prog.server.exceptions.IncorrectValuesTypeException;

import java.io.*;

public interface Serializer<T> {
    default byte[] serialize(T object) throws FailedToDumpsEx {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return baos.toByteArray();
    }

    default T parse(byte[] body) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(body));
        T readStrObj = (T) ois.readObject();
        ois.close();
        return readStrObj;
    }
}
