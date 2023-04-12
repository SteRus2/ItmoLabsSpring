package us.obviously.itmo.prog.common.serializers;


import us.obviously.itmo.prog.common.model.StudyGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupCountingSerializer implements Serializer<Map<String, List<StudyGroup>>> {

    @Override
    public String serialize(Map<String, List<StudyGroup>> object) {
        // TODO: Сериализуем получается
        return String.valueOf(object.toString());
    }

    @Override
    public Map<String, List<StudyGroup>> parse(String body) {
        int key = Integer.parseInt(body);
        // TODO: Распарсивание
        // return new DataInfo(key, new StudyGroup());
        return new HashMap<String, List<StudyGroup>>();
    }
}