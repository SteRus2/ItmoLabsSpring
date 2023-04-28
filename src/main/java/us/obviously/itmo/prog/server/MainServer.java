package us.obviously.itmo.prog.server;

import us.obviously.itmo.prog.client.console.ConsoleColor;
import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.client.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.data.LocalDataCollection;
import us.obviously.itmo.prog.common.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.exceptions.ServerErrorException;
import us.obviously.itmo.prog.server.data.DataStorage;
import us.obviously.itmo.prog.server.exceptions.*;
import us.obviously.itmo.prog.server.net.Server;
import us.obviously.itmo.prog.server.reader.DataReader;
import us.obviously.itmo.prog.server.reader.FileFormat;
import us.obviously.itmo.prog.server.reader.FileFormatReader;

public class MainServer {
    public static int port = 9999;
    public static Server server;
    static {
        ConsoleColor.initColors();
    }
    public static void main(String[] args) {
        if (args.length != 1) {
            Messages.printStatement("Программа принимает единственный обязательный аргумент - путь к файлу.");
            return;
        }
        try {
            DataReader reader = new FileFormatReader(args[0], FileFormat.XML); //TODO обработать использование парсера по названию файла
            LocalDataCollection dataCollection = new DataStorage(reader);
            if (!dataCollection.canSaveData()) {
                Messages.printStatement("~yeОсторожно! Нет права на запись.~=");
            }
            server = new Server(dataCollection, port);
            server.run();
            dataCollection.saveData();
        } catch (CantFindFileException e) {
            Messages.printStatement("~reФайл не найден. Убедитесь в правильности пути и повторите попытку.~=");
        } catch (IncorrectValueException | IncorrectValuesTypeException e) {
            Messages.printStatement("~reНевалидные данные. " + e.getMessage() + "~=");
        } catch (CantParseDataException | FailedToDumpsEx | CantWriteDataException e) {
            Messages.printStatement("~reФайл нечитаем. " + e.getMessage() + "~=");
        } catch (FileNotReadableException e) {
            Messages.printStatement("~reНет разрешение на чтение файла. Воспользуйтесь командой ~grchmod~=");
        } catch (FileNotWritableException e) {
            Messages.printStatement("~reФайл нельзя записывать " + e.getMessage() + "~=");
        } catch (FailedToStartServerException e) {
            Messages.printStatement("~reСервер не запущен: " + e.getMessage() + "~=");
        }
    }
}
