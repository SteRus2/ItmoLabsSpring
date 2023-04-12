package us.obviously.itmo.prog.common.serializers;


import us.obviously.itmo.prog.common.model.Person;

public class PersonSerializer implements Serializer<Person> {

    @Override
    public String serialize(Person object) {
        return String.valueOf(object.toString());
    }

    @Override
    public Person parse(String body) {
        int key = Integer.parseInt(body);
        return new Person();
    }
}