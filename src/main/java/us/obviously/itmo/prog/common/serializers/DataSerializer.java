package us.obviously.itmo.prog.common.serializers;

import us.obviously.itmo.prog.client.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.server.exceptions.CantParseDataException;
import us.obviously.itmo.prog.server.exceptions.FailedToDumpsEx;
import us.obviously.itmo.prog.server.exceptions.IncorrectValuesTypeException;

import java.io.IOException;
import java.util.HashMap;

public class DataSerializer implements Serializer<HashMap<Integer, StudyGroup>> {
//    @Override
//    public String serialize(HashMap<Integer, StudyGroup> object) throws FailedToDumpsEx {
//        StringBuilder builder = new StringBuilder();
//        object.forEach((key, o) -> {
//            builder.append(o.getClass().toString());
//        });
//        return builder.toString();
//    }
//
//    @Override
//    public HashMap<Integer, StudyGroup> parse(String body) throws IOException, ClassNotFoundException {
//        var elements = body.split("&");
//        for (String element : elements) {
//
//        }
//        return Serializer.super.parse(body);
//    }
}