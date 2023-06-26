package us.obviously.itmo.prog.common.server.parser;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import us.obviously.itmo.prog.common.server.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.common.validation.StudyGroupValidation;
import us.obviously.itmo.prog.common.server.exceptions.CantParseDataException;
import us.obviously.itmo.prog.common.server.exceptions.FailedToDumpsEx;
import us.obviously.itmo.prog.common.server.exceptions.IncorrectValuesTypeException;

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
public class XMLParser extends Parser {
    public static final String ROOT_NAME = "StudyGroups";
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
    public HashMap<Integer, StudyGroup> loads(String value) throws IncorrectValueException, IncorrectValuesTypeException, CantParseDataException {
        List<StudyGroup> l1;
        try {
            l1 = xmlMapper.readValue(value, new TypeReference<>() {
            });
        } catch (JsonMappingException e) {
            throw new IncorrectValuesTypeException("Данные в файле имеют некорректный тип");
        } catch (JsonProcessingException e) {
            throw new CantParseDataException("Файл сломан");
        }
        StudyGroupValidation.validateList(l1);
        HashMap<Integer, StudyGroup> result = new HashMap<>();
        for (StudyGroup sg : l1) {
            result.put(sg.getId(), sg);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String dumps(HashMap<Integer, StudyGroup> value) throws FailedToDumpsEx {
        Collection<StudyGroup> values = value.values();
        List<StudyGroup> dataList = new ArrayList<>(values);
        String result;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            xmlMapper.writer().withRootName("StudyGroups").writeValue(byteArrayOutputStream, dataList);
            result = byteArrayOutputStream.toString();
            return result;
        } catch (IOException e) {
            throw new FailedToDumpsEx("Не удалось сжать коллекцию");
        }
    }
}