package us.obviously.itmo.prog.common.serializers;

public interface Serializer<T> {
    String serialize(T object);
    T parse(String body);
}
