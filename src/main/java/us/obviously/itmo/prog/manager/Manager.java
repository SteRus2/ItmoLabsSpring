package us.obviously.itmo.prog.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import us.obviously.itmo.prog.DataCollection;
import us.obviously.itmo.prog.commands.*;
import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.exceptions.UnexpectedArgumentException;
import us.obviously.itmo.prog.reader.DataReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class Manager<T> implements Management {
    private final Scanner scanner;
    private final DataCollection dataCollection;
    private final HashMap<String, AbstractCommand> commands;
    private final List<AbstractCommand> commandsList;
    private Scanner fileScanner;
    private Boolean active;

    public Manager(DataReader reader) throws JsonProcessingException, IncorrectValueException {
        this.dataCollection = new DataCollection(reader);
        this.scanner = new Scanner(System.in);

        this.commands = new HashMap<>();
        this.commandsList = new ArrayList<>();
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
        if (this.fileScanner != null && this.fileScanner.hasNextLine()) return this.fileScanner;
        return this.scanner;
    }

    public String nextChar() {
        if (this.fileScanner != null && this.fileScanner.hasNext()) {
            String line = this.fileScanner.next();
            System.out.print(ConsoleColors.GREEN_BOLD + line + ConsoleColors.RESET);
            return line;
        }
        return this.scanner.next();
    }

    public String nextLine() {
        if (this.fileScanner != null && this.fileScanner.hasNextLine()) {
            String line = this.fileScanner.nextLine();
            System.out.println(ConsoleColors.GREEN_BOLD + line + ConsoleColors.RESET);
            return line;
        }
        return this.scanner.nextLine();
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
    public void save() throws IOException {
        this.dataCollection.saveData();
    }

    /**
     * @param filepath
     */
    @Override
    public void executeScript(String filepath) throws FileNotFoundException {
        var file = new File(filepath);
        this.fileScanner = new Scanner(file);
    }

    private void semanticError() {
    }

    @Override
    public void addCommand(AbstractCommand abstractCommand) {
        this.commands.put(abstractCommand.getKey(), abstractCommand);
        this.commandsList.add(abstractCommand);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean isIdExists(Integer id) {
        return dataCollection.getData().get(id) != null;
    }

    /**
     * @return
     */
    @Override
    public List<AbstractCommand> getCommands() {
        return this.commandsList;
    }

    /**
     * @param key
     * @return
     */
    @Override
    public AbstractCommand getCommand(String key) {
        return this.commands.get(key);
    }

    private boolean waitCommand() {
        System.out.print("> ");
        String line = this.nextLine();
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

    public DataCollection getDataCollection() {
        return dataCollection;
    }

}