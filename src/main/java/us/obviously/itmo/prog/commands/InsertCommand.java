    package us.obviously.itmo.prog.commands;


    import us.obviously.itmo.prog.exceptions.IncorrectValueException;
    import us.obviously.itmo.prog.forms.PersonForm;
    import us.obviously.itmo.prog.forms.PersonFormFields;
    import us.obviously.itmo.prog.forms.StudyGroupForm;
    import us.obviously.itmo.prog.manager.Management;
    import us.obviously.itmo.prog.model.Person;
    import us.obviously.itmo.prog.model.StudyGroup;

    import java.util.HashMap;
    import java.util.Scanner;

    public class InsertCommand extends AbstractCommand {
        public InsertCommand(Management manager) {
            super(manager, "insert", "Добавить новый элемент с заданным ключом");
            addParameter("key", "");
        }

        /**
         *
         */
        @Override
        public void execute(HashMap<String, String> args) {
            var studyGroupForm = new StudyGroupForm(this.manager);
            var key = args.get("key");
            studyGroupForm.run(key);
            StudyGroup studyGroup = studyGroupForm.build();
            this.manager.insertData(studyGroup);
        }
        // null {element}

    }