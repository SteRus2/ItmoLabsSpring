package us.obviously.itmo.prog.server.parser;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import us.obviously.itmo.prog.server.exceptions.CantParseDataException;
import us.obviously.itmo.prog.server.exceptions.FailedToDumpsEx;
import us.obviously.itmo.prog.client.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.server.exceptions.IncorrectValuesTypeException;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.common.validation.StudyGroupValidation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * {@inheritDoc}
 * <br> Формат строки - json
 */
public class JsonParser extends Parser {
    private List<StudyGroup> dataList;
    private final ObjectMapper objectMapper;

    {
        objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        objectMapper.findAndRegisterModules();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HashMap<Integer, StudyGroup> loads(String value) throws IncorrectValueException, IncorrectValuesTypeException, CantParseDataException {
        List<StudyGroup> l1;
        try {
            l1 = objectMapper.readValue(value, new TypeReference<>() {
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
        dataList = new ArrayList<>(values);
        String result;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            objectMapper.writer().writeValue(byteArrayOutputStream, dataList);
            result = byteArrayOutputStream.toString();
            return result;
        } catch (IOException e) {
            throw new FailedToDumpsEx("Не удалось сжать коллекцию");
        }
    }
}
