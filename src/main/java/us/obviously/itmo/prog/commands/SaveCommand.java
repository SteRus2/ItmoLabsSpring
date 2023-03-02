package us.obviously.itmo.prog.commands;

import us.obviously.itmo.prog.manager.Management;

import java.io.IOException;
import java.util.HashMap;

public class SaveCommand extends AbstractCommand {
    /**
     * @param manager
     */
    public SaveCommand(Management manager) {
        super(manager, "save", "Сохранить коллекцию в файл");
    }

    /**
     * @param args
     */
    @Override
    public void execute(HashMap<String, String> args) {
        try {
            this.manager.save();
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении: " + e.getMessage());
        }
    }
}