package us.obviously.itmo.prog.manager;

import us.obviously.itmo.prog.commands.*;
import us.obviously.itmo.prog.exceptions.UnexpectedArgumentException;
import us.obviously.itmo.prog.model.StudyGroup;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class Manager<T> implements Management {
    Scanner scanner = new Scanner(System.in);
    HashMap<String, AbstractCommand> commands;
    Boolean active;

    public Manager() {
        commands = new HashMap<>();
        new HelpCommand(this);
        new InfoCommand(this);
        new ShowCommand(this);
        new InsertCommand(this);
        new UpdateCommand(this);
        new RemoveKeyCommand(this);
        new ClearCommand(this);
        new SaveCommand(this);
        new ExecuteScriptCommand(this);
        new ExitCommand(this);
        new ReplaceIfGreaterCommand(this);
        new RemoveGreaterKeyCommand(this);
        new RemoveLowerKeyCommand(this);
        new GroupCountingByNameCommand(this);
        new FilterGreaterThanGroupAdminCommand(this);
        new PrintFieldAscendingSemesterEnumCommand(this);
    }

    public Scanner getScanner() {
        return this.scanner;
    }

    public void run() {
        this.active = true;
        while (this.active) {
            waitCommand();
        }
    }

    public void stop() {
        this.active = false;
    }

    /**
     *
     */
    @Override
    public void save() {

    }

    /**
     * @param fileName
     */
    @Override
    public void executeScript(String fileName) {

    }

    private void semanticError() {
    }

    @Override
    public void addCommand(AbstractCommand abstractCommand) {
        this.commands.put(abstractCommand.getKey(), abstractCommand);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean isIdExists(Integer id) {
        return false;
    }

    /**
     * @return
     */
    @Override
    public HashMap<String, AbstractCommand> getCommands() {
        return this.commands;
    }

    /**
     * @param key
     * @return
     */
    @Override
    public AbstractCommand getCommand(String key) {
        return this.commands.get(key);
    }

    /**
     * @param studyGroup
     */
    @Override
    public void insertData(StudyGroup studyGroup) {

    }

    private boolean waitCommand() {
        System.out.print("> ");
        String line = this.scanner.nextLine();
        String[] words = line.split(" ");
        String commandName = words[0];
        var command = this.commands.get(commandName.toLowerCase());
        if (command == null) {
            System.out.printf("\"%s\" не является командой. Введите help для просмотра текущих команд.%n", commandName);
            this.semanticError();
            return false;
        }
        try {
            var args = command.parseParameters(Arrays.copyOfRange(words, 1, words.length));

            try {
                command.execute(args);
            } catch (Exception e) {
                 throw new RuntimeException(e);
//                return false;
            }

        } catch (UnexpectedArgumentException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}