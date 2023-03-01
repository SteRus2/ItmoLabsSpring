package us.obviously.itmo.prog.commands;

import us.obviously.itmo.prog.manager.Management;

import java.util.HashMap;

public class ExecuteScriptCommand extends AbstractCommand {

    public ExecuteScriptCommand(Management manager) {
        super(manager, "execute_script", "Считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        this.addParameter("file_name", "Путь к файлу", true);
    }

    /**
     * TODO: FILL
     */
    @Override
    public void execute(HashMap<String, String> args) throws Exception {
        var fileName = args.get("file_name");
        if (fileName == null || fileName.equals("")) {
            throw new Exception("fsf");
        }
        this.manager.executeScript(fileName);
    }
}