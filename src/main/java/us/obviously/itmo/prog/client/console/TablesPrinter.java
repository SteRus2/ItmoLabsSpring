package us.obviously.itmo.prog.client.console;

import us.obviously.itmo.prog.common.model.Semester;
import us.obviously.itmo.prog.common.model.StudyGroup;

import java.util.HashMap;
import java.util.List;

/**
 * Класс для вывода графики
 */
public class TablesPrinter {
    /**
     * Вывод всех групп в виде таблицы
     */
    public static void printStudyGroups(HashMap<Integer, StudyGroup> data) {

        int width = 100;

        StringBuilder builder = new StringBuilder();
        line(builder, width);
        data.forEach((key, group) -> {

            StringBuilder builder1 = new StringBuilder();
            builder1.append("| ");
            var coordinates = "{ %d; %f }".formatted(group.getCoordinates().getX(), group.getCoordinates().getY());
            var space1 = width - 4 - coordinates.length() + 14 + 5;
            var title = (
                    "~bl%s~=" + " "
                            + ConsoleColors.YELLOW + "%s ~=--~cy %s (id: %d) ~=")
                    .formatted(group.getName(), key, group.getOwnerUsername(), group.getOwnerId());
            var left = ("%-" + space1 + "s").formatted(title);
            builder1.append(left).append(coordinates);
            builder1.append(" |%n".formatted());
            builder.append(builder1);


            StringBuilder builder2 = new StringBuilder();
            builder2.append("| ");
            builder2.append(group.getSemesterEnum().name);
            builder2.append(", ").append(group.getFormOfEducation().name);
            if (group.getStudentsCount() != null)
                builder2.append(", учащихся: ").append(group.getStudentsCount());
            if (group.getCreationDate() != null)
                builder2.append(", создана: ").append(group.getCreationDate());
            int space2 = width - builder2.length() - 1;
            builder2.append(("%" + space2 + "s|%n").formatted(""));
            builder.append(builder2);


            StringBuilder builder3 = new StringBuilder();
            builder3.append("| ");
            var admin = group.getGroupAdmin();
            builder3.append("Админ: ").append(admin.getName());
            builder3.append(", ").append(admin.getBirthday());
            if (admin.getEyeColor() != null)
                builder3.append(", глаза: ").append(admin.getEyeColor().name);
            if (admin.getHairColor() != null)
                builder3.append(", волосы: ").append(admin.getHairColor().name);
            if (admin.getNationality() != null)
                builder3.append(", ").append(admin.getNationality().name);
            int space3 = width - builder3.length() - 1;
            builder3.append(("%" + space3 + "s|%n").formatted(""));
            builder.append(builder3);

            line(builder, width);
        });
        Messages.printStatement(builder.toString());
    }

    /**
     * Вывод всех групп в виде таблицы
     */
    public static void printStudyGroups(List<StudyGroup> data) {
        HashMap<Integer, StudyGroup> map = new HashMap<>();
        data.forEach((item) -> map.put(item.getId(), item));
        printStudyGroups(map);
    }

    /**
     * Вывод таблицы семестров
     */
    public static void printSemesters(List<Semester> semesters) {
        int width = 70;
        var builder = new StringBuilder();
        line(builder, width);
        semesters.forEach((semester -> {
            builder.append("| ").append(("%-" + (width - 4) + "s").formatted(semester.name)).append(" |%n".formatted());
            line(builder, width);
        }));
        Messages.printStatement(builder.toString());
    }

    /**
     * Вывод разделителя между формами
     */
    public static void printTableDelimiter() {
        Messages.printStatement("%n~0bk~~~~~~~~=%n");
    }

    /**
     * Добавление разделительной горизонтальной линии в <b>builder</b>
     *
     * @param symbols Ширина линии в символах
     * @return Тот же builder, для сохранения chain-ов
     */
    private static StringBuilder line(StringBuilder builder, Integer symbols) {
        if (symbols == 0) return builder.append("%n".formatted());
        if (symbols > 1) {
            builder.append("+");
            int i = symbols - 2;
            while (i-- > 0) {
                builder.append("-");
            }
        }
        return builder.append("+%n".formatted());
    }
}
