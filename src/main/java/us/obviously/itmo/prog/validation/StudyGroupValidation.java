package us.obviously.itmo.prog.validation;

import us.obviously.itmo.prog.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.forms.Form;
import us.obviously.itmo.prog.model.Coordinates;
import us.obviously.itmo.prog.model.FormOfEducation;
import us.obviously.itmo.prog.model.Semester;
import us.obviously.itmo.prog.model.StudyGroup;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StudyGroupValidation {
    HashMap<String, FormOfEducation> formsOfEducation;
    HashMap<String, Semester> semesters;
    StudyGroup.Builder builder;

    public static void validateList(List<StudyGroup> studyGroups) throws IncorrectValueException {
        for (StudyGroup studyGroup : studyGroups) {
            validate(studyGroup);
        }
        validateIdUnique(studyGroups);
    }

    public static void validate(StudyGroup studyGroup) throws IncorrectValueException {
        validateId(studyGroup.getId());
        validateName(studyGroup.getName());
        validateCoordinates(studyGroup.getCoordinates());
        validateStudentsCount(studyGroup.getStudentsCount());
        validateFormOfEducation(studyGroup.getFormOfEducation());
        validateSemesterEnum(studyGroup.getSemesterEnum());
    }

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

    public static void validateId(Integer value) throws IncorrectValueException {
        if (value == null) throw new IncorrectValueException("Поле id не может быть null.");
        if (value <= 0) throw new IncorrectValueException("Поле id должно быть больше 0.");
    }


    public static void validateName(String value) throws IncorrectValueException {
        if (value == null) throw new IncorrectValueException("Поле name не может быть null.");
        if (value.equals("")) throw new IncorrectValueException("Поле name не может быть пустым.");
    }

    public static void validateCoordinates(Coordinates coordinates) throws IncorrectValueException {
        CoordinatesValidation.validate(coordinates);
    }

    public static void validateStudentsCount(Integer value) throws IncorrectValueException {
        if (value == null) return;
        if (value <= 0) throw new IncorrectValueException("Поле studentsCount должно быть больше 0.");
    }

    public static void validateFormOfEducation(FormOfEducation value) throws IncorrectValueException {
        if (value == null) throw new IncorrectValueException("Поле formOfEducation не может быть null.");
    }

    public static void validateSemesterEnum(Semester value) throws IncorrectValueException {
        if (value == null) throw new IncorrectValueException("Поле semesterEnum не может быть null.");
    }
}
