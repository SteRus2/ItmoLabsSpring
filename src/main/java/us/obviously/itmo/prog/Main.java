package us.obviously.itmo.prog;

import us.obviously.itmo.prog.client.console.ConsoleColor;
import us.obviously.itmo.prog.client.console.Messages;

public class Main {
    public static void main(String[] args) {
        ConsoleColor.initColors();

        if (args.length != 1) {
            Messages.printStatement("Программа принимает единственный обязательный аргумент - путь к файлу.");
            return;
        }
        try {
            /*DataReader reader = new FileFormatReader(args[0], FileFormat.XML); //TODO обработать использование парсера по названию файла
            DataCollection dataCollection = new DataStorage(reader);
            Scanner scanner = new Scanner(System.in);
            Management manager = new Manager<StudyGroup>(dataCollection, scanner);
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
        } catch (BadRequestException e) {
            Messages.printStatement("~reНеверный запрос: " + e.getMessage() + "~=");
        } catch (ServerErrorException e) {
            Messages.printStatement("~Ошибка сервера: " + e.getMessage() + "~=");
        }*/
        } catch (Exception e) {

        }
    }
}