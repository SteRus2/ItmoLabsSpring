package us.obviously.itmo.prog.common.serializers;

import us.obviously.itmo.prog.common.data.DataInfo;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.server.exceptions.FailedToDumpsEx;

import java.io.*;
import java.util.Date;

public class DataInfoSerializer implements Serializer<DataInfo> {
    public byte[] serialize(DataInfo object) throws FailedToDumpsEx {
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

    public DataInfo parse(String body) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(body.getBytes()));
        DataInfo readStrObj = (DataInfo) ois.readObject();
        ois.close();
        return readStrObj;
    }
}
