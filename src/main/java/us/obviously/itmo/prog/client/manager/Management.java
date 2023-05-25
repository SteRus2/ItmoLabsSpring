package us.obviously.itmo.prog.client.manager;

import us.obviously.itmo.prog.client.commands.AbstractCommand;
import us.obviously.itmo.prog.client.exceptions.RecurrentExecuteScripts;
import us.obviously.itmo.prog.common.data.DataCollection;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Интерфейс описывает методы взаимодействия с пользователем
 */
public interface Management {
    /**
     * Запуск цикла Менажёра
     */
    void run();

    /**
     * Остановка цикла Менджера
     */
    void stop();

    /**
     * Вызов скрипта из файла <b>filepath</b>
     *
     * @param filepath Путь к файлу
     * @throws FileNotFoundException   Выбросит исключение, если файл не найден
     * @throws RecurrentExecuteScripts Выбросит исключение, если произошла рекурсия вызовов
     */
    void executeScript(String filepath) throws FileNotFoundException, RecurrentExecuteScripts;

    /**
     * Добавить команду <b>abastractCommand</b> в мапу возможных команд
     *
     * @param abstractCommand Исполняемая команда
     */
    void addCommand(AbstractCommand abstractCommand);

    /**
     * Проверка существования элемента с переданным <b>id</b>
     *
     * @param id Проверяемое id
     * @return <b>true</b> - элемент с данным id существует
     */
    boolean isIdExists(Integer id);

    /**
     * @return Получить следующую введённую строку
     */
    String nextLine();

    /**
     * @return Список всех используемых команда
     */
    List<AbstractCommand> getCommands();

    /**
     * Получить команду по ключу <b>key</b>
     */
    AbstractCommand getCommand(String key);

    /**
     * @return Класс для работы с коллекцией групп
     * @see DataCollection
     */
    DataCollection getDataCollection();
}
