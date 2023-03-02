package us.obviously.itmo.prog;

import com.fasterxml.jackson.core.JsonProcessingException;
import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.manager.Management;
import us.obviously.itmo.prog.manager.Manager;
import us.obviously.itmo.prog.model.StudyGroup;
import us.obviously.itmo.prog.reader.DataReader;
import us.obviously.itmo.prog.reader.XMLReader;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Программа принимает единственный обязательный аргумент - путь к файлу.");
            return;
        }
        try {
            DataReader reader = new XMLReader(args[0]);
            Management manager = new Manager<StudyGroup>(reader);
            manager.run();
        } catch (FileNotFoundException e) {
            System.out.println(ConsoleColors.RED + "Файл не найден. Убедитесь в правильности пути и повторите попытку." + ConsoleColors.RESET);
        } catch (JsonProcessingException e) {
            System.out.println(ConsoleColors.RED + "Файл нечитаем. Убедитесь, что соблюдается что там надо." + ConsoleColors.RESET);
        } catch (IncorrectValueException e) {
            System.out.println(ConsoleColors.RED + "Невалидные данные. " + e.getMessage() + ConsoleColors.RESET);
        }
    }
}