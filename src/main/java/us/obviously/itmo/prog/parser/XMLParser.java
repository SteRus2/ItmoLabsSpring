package us.obviously.itmo.prog.parser;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import us.obviously.itmo.prog.exceptions.FailedToDumpsEx;
import us.obviously.itmo.prog.model.StudyGroup;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

public class XMLParser extends Parser{
    private List<StudyGroup> dataList;
    private XmlMapper xmlMapper;
    {
        xmlMapper = new XmlMapper();
        xmlMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        xmlMapper.findAndRegisterModules();

    }
    @Override
    public HashMap<Integer, StudyGroup> loads(String value) throws JsonProcessingException {
        List<StudyGroup> l1 = xmlMapper.readValue(value, new TypeReference<>() {});

        return null;
    }

    @Override
    public String dumps(HashMap<Integer, StudyGroup> value) throws FailedToDumpsEx {
        Collection<StudyGroup> values = value.values();
        dataList = new ArrayList<>(values);
        String result;
        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();) {
            xmlMapper.writer().withRootName("StudyGroups").writeValue(byteArrayOutputStream, dataList);
            result = byteArrayOutputStream.toString();
            return result;
        } catch (IOException e) {
            throw new FailedToDumpsEx("Не удалось сжать коллекцию");
        }
    }
}
