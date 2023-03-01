package us.obviously.itmo.prog.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class FileReader extends DataReader {
    protected String filePath;
    protected File file;
    protected Scanner scanner;
    protected List<String> strings = new ArrayList<>();
    protected String mainString = "";
    public FileReader(String filePath) {
        this.filePath = filePath;
        file = new File(filePath);
    }
}
