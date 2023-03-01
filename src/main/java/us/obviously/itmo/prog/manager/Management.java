package us.obviously.itmo.prog.manager;

import us.obviously.itmo.prog.commands.AbstractCommand;
import us.obviously.itmo.prog.model.Person;
import us.obviously.itmo.prog.model.StudyGroup;

import java.util.HashMap;
import java.util.Scanner;

public interface Management {
    void run();
    void stop();
    void save();
    void executeScript(String fileName);

    void addCommand(AbstractCommand abstractCommand);
    boolean isIdExists(Integer id);
    public Scanner getScanner();
    HashMap<String, AbstractCommand> getCommands();
    AbstractCommand getCommand(String key);

    void insertData(StudyGroup studyGroup);
}
