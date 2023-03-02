package us.obviously.itmo.prog.forms;

import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.manager.Management;

import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

public class Form {

    Management manager;

    public Form(Management manager) {
        this.manager = manager;
    }

    public void run() {

    }
//
//    public void enterString(Func<String> func, String key, String value) {
//
//        if (value == null) {
//            System.out.printf("Введите %s: ", key);
//            value = this.manager.nextLine();
//        }
//
//        while (true) {
//            try {
//                func.get(value);
//                break;
//            } catch (final IncorrectValueException e) {
//                System.out.println("Ошибка при вводе %s: ".formatted(key) + e.getMessage());
//                System.out.printf("Введите %s: ", key);
//                value = this.manager.nextLine();
//            }
//        }
//        if (value == null) {
//            System.out.println(ConsoleColors.GREEN_BOLD + "В поле %s успешно записано null.".formatted(key) + ConsoleColors.RESET);
//        } else {
//            System.out.println(ConsoleColors.GREEN_BOLD + "В поле %s успешно записано %s.".formatted(key, value) + ConsoleColors.RESET);
//        }
//    }
//
//    public void enterString(Func<String> func, String key) {
//        this.enterString(func, key, null);
//    }
//
//
//    public void enterDate(Func<ZonedDateTime> func, String key, ZonedDateTime value) {
//
//
////        if (value == null) {
////            System.out.printf("Введите %s: ", key);
////            value = this.manager();
////        }
//
//        String userInput = null;
//
//        while (true) {
//            try {
//                func.get(value);
//                break;
//            } catch (final IncorrectValueException e) {
//                System.out.println("Ошибка при вводе %s: ".formatted(key) + e.getMessage());
//                System.out.printf("Введите %s: ", key);
//                userInput = this.manager.nextLine();
//
//                String[] parts = userInput.split("\\+");
//                if (parts.length != 2) {
//                    System.out.println("Ошибка формата.");
//                    continue;
//                }
//
//
//                try {
//                    String datetime = parts[0] + "T00:00:00+" + parts[1];
//                    value = ZonedDateTime.parse(datetime);
//                } catch (DateTimeParseException ec) {
//                    System.out.println(ec.getMessage());
//                }
//            }
//        }
//        if (value == null) {
//            System.out.println(ConsoleColors.GREEN_BOLD + "В поле %s успешно записано null.".formatted(key) + ConsoleColors.RESET);
//        } else {
//            System.out.println(ConsoleColors.GREEN_BOLD + "В поле %s успешно записано %s.".formatted(key, value) + ConsoleColors.RESET);
//        }
//    }
//
//    public void enterDate(Func<ZonedDateTime> func, String key) {
//        this.enterDate(func, key, null);
//    }
//
//    public void enterInteger(Func<Integer> func, String key, Integer value) {
//
//        while (true) {
//            try {
//                System.out.printf("Введите %s: ", key);
//                if (value == null) {
//                    System.out.printf("Введите %s: ", key);
//                    var rawValue = this.manager.nextLine();
//                    try {
//                        value = Integer.getInteger(rawValue);
//                    } catch (NumberFormatException exc) {
//                        throw new RuntimeException(exc);
//                    }
//                }
//
//                func.get(value);
//                break;
//            } catch (final IncorrectValueException e) {
//                System.out.println("Ошибка при вводе %s: ".formatted(key) + e.getMessage());
//            }
//        }
//        if (value == null) {
//            System.out.println(ConsoleColors.GREEN_BOLD + "В поле %s успешно записано null.".formatted(key) + ConsoleColors.RESET);
//        } else {
//            System.out.println(ConsoleColors.GREEN_BOLD + "В поле %s успешно записано %s.".formatted(key, value) + ConsoleColors.RESET);
//        }
//    }
//
//    public void enterInteger(Func<Integer> func, String key) {
//        this.enterInteger(func, key, null);
//    }
//
//    public <T> void enterSelect(Func<T> func, String key, HashMap<String, SelectChoice<T>> choices) {
//        choices.forEach((choiceKey, choice) -> {
//            System.out.printf("%s - %s%n", choiceKey, choice.description());
//        });
//
//
//        System.out.printf("Введите %s: ", key);
//        String userInput = this.manager.nextLine();
//
//        SelectChoice<T> value = null;
//
//        while (true) {
//            try {
//                value = choices.get(userInput);
//                if (value != null) {
//                    func.get(value.value());
//                } else {
//                    func.get(null);
//                }
//                break;
//            } catch (final IncorrectValueException e) {
//                System.out.println("Ошибка при вводе %s: ".formatted(key) + e.getMessage());
//                System.out.printf("Введите %s: ", key);
//                userInput = this.manager.nextLine();
//            }
//        }
//
//        if (value == null) {
//            System.out.println(ConsoleColors.GREEN_BOLD + "В поле %s успешно записано null.".formatted(key) + ConsoleColors.RESET);
//        } else {
//            System.out.println(ConsoleColors.GREEN_BOLD + "В поле %s успешно записано %s.".formatted(key, value.description()) + ConsoleColors.RESET);
//        }
//    }
//
//    interface Func<T> {
//        void get(T data) throws IncorrectValueException;
//    }
}
