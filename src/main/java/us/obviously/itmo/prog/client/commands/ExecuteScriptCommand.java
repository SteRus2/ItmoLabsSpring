package us.obviously.itmo.prog.client.commands;

import us.obviously.itmo.prog.client.console.ConsoleColors;
import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.client.exceptions.RecurrentExecuteScripts;
import us.obviously.itmo.prog.client.manager.Management;

import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * Команда для исполнения скрипта
 */
public class ExecuteScriptCommand extends AbstractCommand {

    public ExecuteScriptCommand(Management manager) {
        super(manager, "execute_script", "Считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        this.addParameter("file_name", "Путь к файлу", true);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void execute(HashMap<String, String> args) {
        var filepath = args.get("file_name");
        if (filepath == null || filepath.equals("")) {
            Messages.printStatement("~yefile_name ~=" +
                    "- обязательное поле.");
            return;
        }
        try {
            this.manager.executeScript(filepath);
        } catch (FileNotFoundException e) {
            Messages.printStatement("Файл не найден.");
        } catch (RecurrentExecuteScripts e) {
            Messages.printStatement(ConsoleColors.RED + "Произошла рекурсия. Пропущен вызов скрипта: " + e.getMessage() + "~=");
        }
    }
}