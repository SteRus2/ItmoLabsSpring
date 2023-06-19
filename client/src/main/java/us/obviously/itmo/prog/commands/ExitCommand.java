package us.obviously.itmo.prog.commands;

import us.obviously.itmo.prog.manager.Management;

import java.util.HashMap;


/**
 * Команда для завершения работы программы (без сохранения)
 *
 * @see SaveCommand
 */
public class ExitCommand extends AbstractCommand {
    public ExitCommand(Management manager) {
        super(manager, "exit", "Завершить программу (без сохранения в файл)");

    }

    /**
     * @inheritDoc
     */
    @Override
    public void execute(HashMap<String, String> args) {
        this.manager.stop();

    }

}