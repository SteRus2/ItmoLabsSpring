package us.obviously.itmo.prog.common.serializers;

import us.obviously.itmo.prog.common.model.StudyGroup;

import java.util.HashMap;

public class DataSerializer implements Serializer<HashMap<Integer, StudyGroup>> {

    @Override
    public String serialize(HashMap<Integer, StudyGroup> object) {
        // TODO: Сериализуем получается
        return String.valueOf(object.size());
    }

    @Override
    public HashMap<Integer, StudyGroup> parse(String body) {
        int key = Integer.parseInt(body);
        // TODO: Распарсивание
        return new HashMap<Integer, StudyGroup>();
    }
}