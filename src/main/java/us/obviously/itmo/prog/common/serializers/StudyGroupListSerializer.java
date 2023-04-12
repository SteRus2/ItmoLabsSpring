package us.obviously.itmo.prog.common.serializers;


import us.obviously.itmo.prog.common.model.StudyGroup;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StudyGroupListSerializer implements Serializer<List<StudyGroup>> {

    @Override
    public String serialize(List<StudyGroup> object) {
        // TODO: Сериализуем получается
        return String.valueOf(object);
    }

    @Override
    public List<StudyGroup> parse(String body) {
        int key = Integer.parseInt(body);
        // TODO: Распарсивание
        // return new DataInfo(key, new StudyGroup());
        return new LinkedList<>();
    }
}