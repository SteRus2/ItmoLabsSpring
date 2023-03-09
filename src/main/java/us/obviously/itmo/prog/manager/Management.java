package us.obviously.itmo.prog.manager;

import us.obviously.itmo.prog.DataCollection;
import us.obviously.itmo.prog.commands.AbstractCommand;
import us.obviously.itmo.prog.exceptions.CantWriteDataException;
import us.obviously.itmo.prog.exceptions.FailedToDumpsEx;
import us.obviously.itmo.prog.exceptions.RecurrentExecuteScripts;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public interface Management {
    void run();

    void stop();

    void save() throws FailedToDumpsEx, CantWriteDataException;

    void executeScript(String fileName) throws FileNotFoundException, RecurrentExecuteScripts;

    void addCommand(AbstractCommand abstractCommand);

    boolean isIdExists(Integer id);

    public Scanner getScanner();

    public String nextLine();

    public String nextChar();

    List<AbstractCommand> getCommands();

    AbstractCommand getCommand(String key);

    DataCollection getDataCollection();
}
