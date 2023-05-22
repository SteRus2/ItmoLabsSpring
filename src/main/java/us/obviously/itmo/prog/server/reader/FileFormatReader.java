package us.obviously.itmo.prog.server.reader;

import us.obviously.itmo.prog.client.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.server.exceptions.*;
import us.obviously.itmo.prog.server.parser.JsonParser;
import us.obviously.itmo.prog.server.parser.XMLParser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Scanner;

/**
 * {@inheritDoc}
 */
@Deprecated
public class FileFormatReader extends FileReader {
    private String baseString;
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
        baseString = switch (ff){
            case XML -> "<%s></%s>".formatted(XMLParser.ROOT_NAME, XMLParser.ROOT_NAME);
            case JSON -> "{}";
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HashMap<Integer, StudyGroup> getData() throws IncorrectValueException, IncorrectValuesTypeException, CantParseDataException, CantFindFileException, FileNotReadableException {
        if (!Files.isReadable(file.toPath())){
            throw new FileNotReadableException("Извините, файл не читаем");
        }
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
        if (mainString.trim().equals("")){
            mainString = baseString;
        }
        return parser.loads(mainString);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveData(HashMap<Integer, StudyGroup> data) throws CantWriteDataException, FailedToDumpsEx, FileNotWritableException {
        if (!Files.isWritable(file.toPath())){
            throw new FileNotWritableException("Извините, нет разрешения на запись");
        }
        mainString = parser.dumps(data);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            byte[] buffer = mainString.getBytes();
            fos.write(buffer, 0, buffer.length);
        } catch (IOException e) {
            throw new CantWriteDataException("Не удалось сохранить данные");
        }
    }
}
