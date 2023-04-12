package us.obviously.itmo.prog.common.serializers;


import us.obviously.itmo.prog.common.model.Semester;

import java.util.LinkedList;
import java.util.List;

public class SemesterListSerializer implements Serializer<List<Semester>> {

    @Override
    public String serialize(List<Semester> object) {
        // TODO: Сериализуем получается
        return String.valueOf(object);
    }

    @Override
    public List<Semester> parse(String body) {
        int key = Integer.parseInt(body);
        // TODO: Распарсивание
        // return new DataInfo(key, new Semester());
        return new LinkedList<>();
    }
}