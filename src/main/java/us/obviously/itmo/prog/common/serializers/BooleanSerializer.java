package us.obviously.itmo.prog.common.serializers;


import java.util.Objects;

public class BooleanSerializer implements Serializer<Boolean> {

    @Override
    public String serialize(Boolean object) {
        return object.toString();
    }

    @Override
    public Boolean parse(String body) {
        return Objects.equals(body, "true");
    }
}