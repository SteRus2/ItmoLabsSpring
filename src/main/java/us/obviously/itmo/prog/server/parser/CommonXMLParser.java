package us.obviously.itmo.prog.server.parser;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import us.obviously.itmo.prog.client.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.common.validation.StudyGroupValidation;
import us.obviously.itmo.prog.server.exceptions.CantParseDataException;
import us.obviously.itmo.prog.server.exceptions.FailedToDumpsEx;
import us.obviously.itmo.prog.server.exceptions.IncorrectValuesTypeException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * {@inheritDoc}
 * <br> Формат строки - XML
 */
public class CommonXMLParser<T> extends CommonParser<T> {
    private List<StudyGroup> dataList;
    public static String ROOT_NAME = "StudyGroups";
    private final XmlMapper xmlMapper;

    {
        xmlMapper = new XmlMapper();
        xmlMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        xmlMapper.findAndRegisterModules();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T loads(String value) throws IncorrectValueException, IncorrectValuesTypeException, CantParseDataException {
        List<T> l1;
        try {
            l1 = xmlMapper.readValue(value, new TypeReference<>() {});
        } catch (JsonMappingException e) {
            throw new IncorrectValuesTypeException("Данные в файле имеют некорректный тип");
        } catch (JsonProcessingException e) {
            throw new CantParseDataException("Файл сломан");
        }
        return l1.get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String dumps(T value) throws FailedToDumpsEx {
        String result;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            xmlMapper.writer().withRootName("StudyGroups").writeValue(byteArrayOutputStream, value);
            result = byteArrayOutputStream.toString();
            return result;
        } catch (IOException e) {
            throw new FailedToDumpsEx("Не удалось сжать коллекцию");
        }
    }
}
