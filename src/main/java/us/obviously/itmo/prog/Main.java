package us.obviously.itmo.prog;

import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.exceptions.CantFindFileException;
import us.obviously.itmo.prog.exceptions.CantParseDataException;
import us.obviously.itmo.prog.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.exceptions.IncorrectValuesTypeException;
import us.obviously.itmo.prog.manager.Management;
import us.obviously.itmo.prog.manager.Manager;
import us.obviously.itmo.prog.model.StudyGroup;
import us.obviously.itmo.prog.reader.DataReader;
import us.obviously.itmo.prog.reader.FileFormat;
import us.obviously.itmo.prog.reader.FileFormatReader;

public class Main {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Программа принимает единственный обязательный аргумент - путь к файлу.");
            return;
        }
        try {
            DataReader reader = new FileFormatReader(args[0], FileFormat.XML); //TODO обработать использование парсера по названию файла
            Management manager = new Manager<StudyGroup>(reader);
            manager.run();
        } catch (CantFindFileException e) {
            System.out.println(ConsoleColors.RED + "Файл не найден. Убедитесь в правильности пути и повторите попытку." + ConsoleColors.RESET);
        } catch (IncorrectValueException | IncorrectValuesTypeException e) {
            System.out.println(ConsoleColors.RED + "Невалидные данные. " + e.getMessage() + ConsoleColors.RESET);
        } catch (CantParseDataException e) {
            System.out.println(ConsoleColors.RED + "Файл нечитаем. " + e.getMessage() + ConsoleColors.RESET);
        }
    }
}