package us.obviously.itmo.prog.validation;

import us.obviously.itmo.prog.server.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.model.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Класс валидации полей Учебной Группы и валидации уникальных id всех Групп
 */
public class StudyGroupValidation {
    /**
     * Валидация списка Групп <b>studyGroups</b>
     * 1) Проверяется, что каждая Группа валидна
     * 2) Проверяется, что id Групп уникальны
     *
     * @param studyGroups Список Групп
     * @throws IncorrectValueException Выбросит исключение, если список не прошёл валидацию
     * @see StudyGroupValidation#validate(StudyGroup)
     * @see StudyGroupValidation#validateIdUnique(List)
     */
    public static void validateList(List<StudyGroup> studyGroups) throws IncorrectValueException {
        for (StudyGroup studyGroup : studyGroups) {
            validate(studyGroup);
        }
        validateIdUnique(studyGroups);
    }

    /**
     * Валидация Группы, то есть проверка, что каждое её поле валидно.
     *
     * @param studyGroup Группа
     * @throws IncorrectValueException Выбросит исключение, если Группа не прошла валидацию
     */
    public static void validate(StudyGroup studyGroup) throws IncorrectValueException {
        validateId(studyGroup.getId());
        validateName(studyGroup.getName());
        validateCoordinates(studyGroup.getCoordinates());
        validateStudentsCount(studyGroup.getStudentsCount());
        validateFormOfEducation(studyGroup.getFormOfEducation());
        validateSemesterEnum(studyGroup.getSemesterEnum());
        validateGroupAdmin(studyGroup.getGroupAdmin());
    }

    /**
     * Проверка уникальности id у списка Групп <b>studyGroups</b>
     *
     * @param studyGroups Список Групп
     * @throws IncorrectValueException Выбросит исключение, если Группа не прошла валидацию
     */
    public static void validateIdUnique(List<StudyGroup> studyGroups) throws IncorrectValueException {
        Set<Integer> ids = new HashSet<>();
        for (StudyGroup studyGroup : studyGroups) {
            var id = studyGroup.getId();
            if (ids.contains(id)) {
                throw new IncorrectValueException("Поле id должно быть уникальным");
            }
            ids.add(id);
        }
    }

    /**
     * Проверка валидности id для Группы (без проверки уникальности)
     *
     * @param value Проверяемое значение id
     * @throws IncorrectValueException Выбросит исключение, если значение невалидно
     * @see StudyGroupValidation#validateIdUnique(List)
     */
    public static void validateId(Integer value) throws IncorrectValueException {
        if (value == null) throw new IncorrectValueException("Поле id не может быть null.");
        if (value <= 0) throw new IncorrectValueException("Поле id должно быть больше 0.");
    }

    /**
     * Проверка валидности имени для Группы
     *
     * @param value Проверяемое значение имени
     * @throws IncorrectValueException Выбросит исключение, если значение невалидно
     */
    public static void validateName(String value) throws IncorrectValueException {
        if (value == null) throw new IncorrectValueException("Поле name не может быть null.");
        if (value.equals("")) throw new IncorrectValueException("Поле name не может быть пустым.");
    }

    /**
     * Проверка валидности координат для Группы
     *
     * @param coordinates Проверяемое значение координат
     * @throws IncorrectValueException Выбросит исключение, если значение невалидно
     */
    public static void validateCoordinates(Coordinates coordinates) throws IncorrectValueException {
        CoordinatesValidation.validate(coordinates);
    }

    /**
     * Проверка валидности количества студентов для Группы
     *
     * @param value Проверяемое значение количества студентов
     * @throws IncorrectValueException Выбросит исключение, если значение невалидно
     */
    public static void validateStudentsCount(Integer value) throws IncorrectValueException {
        if (value == null) return;
        if (value <= 0) throw new IncorrectValueException("Поле studentsCount должно быть больше 0.");
    }

    /**
     * Проверка валидности формы обучения для Группы
     *
     * @param value Проверяемое значение формы обучения
     * @throws IncorrectValueException Выбросит исключение, если значение невалидно
     */
    public static void validateFormOfEducation(FormOfEducation value) throws IncorrectValueException {
        if (value == null) throw new IncorrectValueException("Поле formOfEducation не может быть null.");
    }

    /**
     * Проверка валидности семестра для Группы
     *
     * @param value Проверяемое значение семестра
     * @throws IncorrectValueException Выбросит исключение, если значение невалидно
     */
    public static void validateSemesterEnum(Semester value) throws IncorrectValueException {
        if (value == null) throw new IncorrectValueException("Поле semesterEnum не может быть null.");
    }

    /**
     * Проверка валидности админа для Группы
     *
     * @param value Проверяемое значение админа
     * @throws IncorrectValueException Выбросит исключение, если значение невалидно
     */
    public static void validateGroupAdmin(Person value) throws IncorrectValueException {
        PersonValidation.validate(value);
    }
}
