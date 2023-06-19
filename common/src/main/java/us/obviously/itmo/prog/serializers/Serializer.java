package us.obviously.itmo.prog.serializers;

import java.io.*;

public final class Serializer<T> {
    public byte[] serialize(T object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            oos.close();
        } catch (IOException ignored) {
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
