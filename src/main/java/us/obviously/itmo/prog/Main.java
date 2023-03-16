package us.obviously.itmo.prog;

import us.obviously.itmo.prog.console.ConsoleColor;
import us.obviously.itmo.prog.console.Messages;
import us.obviously.itmo.prog.exceptions.*;
import us.obviously.itmo.prog.manager.Management;
import us.obviously.itmo.prog.manager.Manager;
import us.obviously.itmo.prog.model.StudyGroup;
import us.obviously.itmo.prog.reader.DataReader;
import us.obviously.itmo.prog.reader.FileFormat;
import us.obviously.itmo.prog.reader.FileFormatReader;

public class Main {
    public static void main(String[] args) {
        ConsoleColor.initColors();

        if (args.length != 1) {
            Messages.printStatement("Программа принимает единственный обязательный аргумент - путь к файлу.");
            return;
        }
        try {
            DataReader reader = new FileFormatReader(args[0], FileFormat.XML); //TODO обработать использование парсера по названию файла
            Management manager = new Manager<StudyGroup>(reader);
            if (!manager.getDataCollection().canSaveData()) {
                Messages.printStatement("~yeОсторожно! Нет права на запись.~=");
            }
            manager.run();
        } catch (CantFindFileException e) {
            Messages.printStatement("~reФайл не найден. Убедитесь в правильности пути и повторите попытку.~=");
        } catch (IncorrectValueException | IncorrectValuesTypeException e) {
            Messages.printStatement("~reНевалидные данные. " + e.getMessage() + "~=");
        } catch (CantParseDataException e) {
            Messages.printStatement("~reФайл нечитаем. " + e.getMessage() + "~=");
        } catch (FileNotReadableException e) {
            Messages.printStatement("~reНет разрешение на чтение файла. Воспользуйтесь командой ~grchmod~=");
        }
    }
}