package us.obviously.itmo.prog.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlainFileReader {

    protected List<String> strings = new ArrayList<>();
    String filePath;
    File file;
    Scanner scanner;

    public PlainFileReader(String filePath) throws FileNotFoundException {
        this.filePath = filePath;
        this.file = new File(filePath);
        this.scanner = new Scanner(file);
    }

    public List<String> getData() {
        while (this.scanner.hasNextLine()) {
            String line = this.scanner.nextLine().trim();
            this.strings.add(line);
        }
        this.scanner.close();
        return this.strings;
    }

    public void saveData(List<String> data) throws IOException {
        String resultString = String.join("\n", data);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            byte[] buffer = resultString.getBytes();
            fos.write(buffer, 0, buffer.length);
        }
    }
}
