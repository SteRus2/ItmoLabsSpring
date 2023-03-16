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

/**
 * {@inheritDoc}
 */
public class FileFormatReader extends FileReader {
    /**
     * Конструктор, задающий путь до файла и формат
     *
     * @param filePath Путь до файла
     * @param ff       Формат файла
     * @see FileFormat
     */

    public FileFormatReader(String filePath, FileFormat ff) {
        super(filePath);
        parser = switch (ff) {
            case XML -> new XMLParser();
            case JSON -> new JsonParser();
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HashMap<Integer, StudyGroup> getData() throws IncorrectValueException, IncorrectValuesTypeException, CantParseDataException, CantFindFileException {
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                strings.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new CantFindFileException("Файл не найден");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        mainString = String.join("", strings);
        return parser.loads(mainString);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveData(HashMap<Integer, StudyGroup> data) throws CantWriteDataException, FailedToDumpsEx {
        mainString = parser.dumps(data);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            byte[] buffer = mainString.getBytes();
            fos.write(buffer, 0, buffer.length);
        } catch (IOException e) {
            throw new CantWriteDataException("Не удалось сохранить данные");
        }
    }
}
