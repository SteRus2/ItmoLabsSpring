package us.obviously.itmo.prog.reader;

import com.fasterxml.jackson.core.JsonProcessingException;
import us.obviously.itmo.prog.model.StudyGroup;
import us.obviously.itmo.prog.parser.XMLParser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class XMLReader extends FileReader{

    public XMLReader(String filePath) throws FileNotFoundException {
        super(filePath);
        parser = new XMLParser();
        scanner = new Scanner(file);
    }

    @Override
    public HashMap<Integer, StudyGroup> getData() throws JsonProcessingException {
        while (scanner.hasNextLine()){
            String line = scanner.nextLine().trim();
            strings.add(line);
        }
        scanner.close();
        mainString = String.join("", strings);
        return parser.loads(mainString);
    }

    @Override
    public void saveData(HashMap<Integer, StudyGroup> data) throws IOException {
        mainString = parser.dumps(data);
        try(FileOutputStream fos = new FileOutputStream(file)){
            byte[] buffer = mainString.getBytes();
            fos.write(buffer, 0, buffer.length);
        }
    }
}
