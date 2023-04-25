package us.obviously.itmo.prog.common.serializers;

import us.obviously.itmo.prog.client.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.server.exceptions.CantParseDataException;
import us.obviously.itmo.prog.server.exceptions.FailedToDumpsEx;
import us.obviously.itmo.prog.server.exceptions.IncorrectValuesTypeException;
import us.obviously.itmo.prog.server.parser.CommonXMLParser;
import us.obviously.itmo.prog.server.parser.XMLParser;

public interface Serializer<T> {
    default String serialize(T object) throws FailedToDumpsEx {
        var parser = new CommonXMLParser<T>();
        return parser.dumps(object);
    }
    default T parse(String body) throws IncorrectValuesTypeException, IncorrectValueException, CantParseDataException {
        //var parser = new CommonXMLParser<T>();
        var parser = new XMLParser();
        return (T) parser.loads(body);
    }
}
