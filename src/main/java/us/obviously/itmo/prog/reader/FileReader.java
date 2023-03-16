package us.obviously.itmo.prog.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Класс, позволяющий считывать данные из файла
 * @see FileFormatReader
 */
public abstract class FileReader extends DataReader {
    protected String filePath;
    protected File file;
    protected Scanner scanner;
    protected List<String> strings = new ArrayList<>();
    protected String mainString = "";

    /**
     * Конструктор, позволяющий задать путь до файла
     * @param filePath Путь до файла
     */
    public FileReader(String filePath) {
        this.filePath = filePath;
        file = new File(filePath);
    }
}
