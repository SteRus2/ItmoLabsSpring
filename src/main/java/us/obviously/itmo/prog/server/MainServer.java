package us.obviously.itmo.prog.server;

import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.client.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.client.manager.Management;
import us.obviously.itmo.prog.client.manager.Manager;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.server.data.DataStorage;
import us.obviously.itmo.prog.server.exceptions.CantFindFileException;
import us.obviously.itmo.prog.server.exceptions.CantParseDataException;
import us.obviously.itmo.prog.server.exceptions.FileNotReadableException;
import us.obviously.itmo.prog.server.exceptions.IncorrectValuesTypeException;
import us.obviously.itmo.prog.server.reader.DataReader;
import us.obviously.itmo.prog.server.reader.FileFormat;
import us.obviously.itmo.prog.server.reader.FileFormatReader;

import java.io.IOException;

public class MainServer {
    public static int port = 9999;
    public static Server server;
    public static void main(String[] args) {
        if (args.length != 1) {
            Messages.printStatement("Программа принимает единственный обязательный аргумент - путь к файлу.");
            return;
        }
        try {
            DataReader reader = new FileFormatReader(args[0], FileFormat.XML); //TODO обработать использование парсера по названию файла
            DataCollection dataCollection = new DataStorage(reader);
            if (!dataCollection.canSaveData()) {
                Messages.printStatement("~yeОсторожно! Нет права на запись.~=");
            }
            server = new Server(dataCollection);
            try {
                server.run(port);
            } catch (IOException e) {
                //TODO Exceptions
            }
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
