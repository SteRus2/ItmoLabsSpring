package us.obviously.itmo.prog.reader;

import us.obviously.itmo.prog.exceptions.*;
import us.obviously.itmo.prog.model.StudyGroup;
import us.obviously.itmo.prog.parser.JsonParser;
import us.obviously.itmo.prog.parser.XMLParser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class FileFormatReader extends FileReader{

    public FileFormatReader(String filePath, FileFormat ff) throws FileNotFoundException {
        super(filePath);
        parser = switch (ff){
            case XML -> new XMLParser();
            case JSON -> new JsonParser();
        };
        scanner = new Scanner(file);
    }

    @Override
    public HashMap<Integer, StudyGroup> getData() throws IncorrectValueException, IncorrectValuesTypeException, CantParseDataException {
        while (scanner.hasNextLine()){
            String line = scanner.nextLine().trim();
            strings.add(line);
        }
        scanner.close();
        mainString = String.join("", strings);
        return parser.loads(mainString);
    }

    @Override
    public void saveData(HashMap<Integer, StudyGroup> data) throws CantWriteDataException, FailedToDumpsEx {
        mainString = parser.dumps(data);
        try(FileOutputStream fos = new FileOutputStream(file)){
            byte[] buffer = mainString.getBytes();
            fos.write(buffer, 0, buffer.length);
        } catch (IOException e){
            throw new CantWriteDataException("Не удалось сохранить данные");
        }
    }
}
