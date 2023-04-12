package us.obviously.itmo.prog.common.serializers;

import us.obviously.itmo.prog.common.data.DataInfo;
import us.obviously.itmo.prog.common.model.StudyGroup;

import java.util.Date;

public class DataInfoSerializer implements Serializer<DataInfo> {

    @Override
    public String serialize(DataInfo object) {
        // TODO: Сериализуем получается
        return String.valueOf(object.getCount());
    }

    @Override
    public DataInfo parse(String body) {
        int key = Integer.parseInt(body);
        // TODO: Распарсивание
        // return new DataInfo(key, new StudyGroup());
        return new DataInfo("", new Date(), 1);
    }
}