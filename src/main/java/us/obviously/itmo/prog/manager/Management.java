package us.obviously.itmo.prog.manager;

import us.obviously.itmo.prog.DataCollection;
import us.obviously.itmo.prog.commands.AbstractCommand;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public interface Management {
    void run();

    void stop();

    void save() throws IOException;

    void executeScript(String fileName) throws FileNotFoundException;

    void addCommand(AbstractCommand abstractCommand);

    boolean isIdExists(Integer id);

    public Scanner getScanner();

    public String nextLine();

    public String nextChar();

    List<AbstractCommand> getCommands();

    AbstractCommand getCommand(String key);

    DataCollection getDataCollection();
}
