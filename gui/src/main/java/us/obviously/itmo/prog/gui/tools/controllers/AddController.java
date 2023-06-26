package us.obviously.itmo.prog.gui.tools.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import us.obviously.itmo.prog.common.model.Coordinates;
import us.obviously.itmo.prog.common.model.Person;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.common.server.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.server.exceptions.UsedKeyException;
import us.obviously.itmo.prog.gui.Main;
import us.obviously.itmo.prog.gui.i18n.Internalization;
import us.obviously.itmo.prog.gui.i18n.Language;
import us.obviously.itmo.prog.gui.tools.fields.*;

import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class AddController extends BaseController {

    public GridPane grid;
    public Button submitButton;
    public Button cancelButton;
    private List<AbstractField<?, ?, ?>> fieldList;
    private GroupNameField groupNameField;
    private CoordinatesXField coordinatesXField;
    private CoordinatesYField coordinatesYField;
    private StudentsCountField studentsCountField;
    private FormOfEducationField formOfEducationField;
    private SemesterField semesterField;
    private AdminNameField adminNameField;
    private AdminBirthdayField birthdayField;
    private AdminEyeColorField eyeColorField;
    private AdminHairColorField hairColorField;
    private AdminNationalityField nationalityField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(25, 25, 25, 25));

            fieldList = new LinkedList<>();

            int currentIndex = 0;

            groupNameField = new GroupNameField();
            groupNameField.addToGrid(grid, currentIndex++);
            groupNameField.getControl().setOnAction(this::onEnter);
            fieldList.add(groupNameField);

            coordinatesXField = new CoordinatesXField();
            coordinatesXField.addToGrid(grid, currentIndex++);
            fieldList.add(coordinatesXField);

            coordinatesYField = new CoordinatesYField();
            coordinatesYField.addToGrid(grid, currentIndex++);
            fieldList.add(coordinatesYField);

            studentsCountField = new StudentsCountField();
            studentsCountField.addToGrid(grid, currentIndex++);
            fieldList.add(studentsCountField);

            formOfEducationField = new FormOfEducationField();
            formOfEducationField.addToGrid(grid, currentIndex++);
            fieldList.add(formOfEducationField);

            semesterField = new SemesterField();
            semesterField.addToGrid(grid, currentIndex++);;
            fieldList.add(semesterField);

            /* ADMIN FIELDS */

            adminNameField = new AdminNameField();
            adminNameField.addToGrid(grid, currentIndex++);
            adminNameField.getControl().setOnAction(this::onEnter);
            fieldList.add(adminNameField);

            birthdayField = new AdminBirthdayField();
            birthdayField.addToGrid(grid, currentIndex++);
            fieldList.add(birthdayField);

            eyeColorField = new AdminEyeColorField();
            eyeColorField.addToGrid(grid, currentIndex++);
            fieldList.add(eyeColorField);

            hairColorField = new AdminHairColorField();
            hairColorField.addToGrid(grid, currentIndex++);
            fieldList.add(hairColorField);

            nationalityField = new AdminNationalityField();
            nationalityField.addToGrid(grid, currentIndex++);
            fieldList.add(nationalityField);

            /* ADMIN FIELDS FINISH */
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }

    @FXML
    private void apply(ActionEvent actionEvent) {
        if (this.validate()) {
            var groupName = groupNameField.getValue();
            var coordinatesX = coordinatesXField.getValue();
            var coordinatesY = coordinatesYField.getValue();
            var studentsCount = studentsCountField.getValue();
            var formOfEducation = formOfEducationField.getValue();
            var semester = semesterField.getValue();
            var adminName = adminNameField.getValue();
            var birthday = birthdayField.getValue();
            var eyeColor = eyeColorField.getValue();
            var hairColor = hairColorField.getValue();
            var nationality = nationalityField.getValue();

            Person person = new Person(adminName, birthday, eyeColor, hairColor, nationality);
            Coordinates coordinates = new Coordinates(coordinatesX, coordinatesY);
            var dateNow = new Date();
            StudyGroup studyGroup = new StudyGroup(null, groupName, coordinates, dateNow, studentsCount, formOfEducation, semester, person);
            try {
                Main.manager.getDataCollection().insertItem(studyGroup, -1);
                errorText.setText("FINE))");
            } catch (UsedKeyException e) {
                errorText.setText("USED KEY");

            } catch (BadRequestException e) {
                errorText.setText("BAD RQEUS" + e.getMessage());
            }
        }
    }

    @FXML
    private void onEnter(ActionEvent actionEvent) {
        this.apply(actionEvent);
    }

    private boolean validate() {
        var dirtyFields = new LinkedList<AbstractField<?, ?, ?>>();
        this.fieldList.forEach(field -> {
            var errorList = field.validate();
            if (errorList.isEmpty()) return;
            dirtyFields.add(field);
        });
        if (dirtyFields.isEmpty()) {
            return true;
        }
        dirtyFields.getFirst().getControl().requestFocus();
        return false;
    }

    @Override
    public void setBundle(Language language) {
        submitButton.setText(Internalization.getTranslation("tool.add.submit"));
        cancelButton.setText(Internalization.getTranslation("tool.add.cancel"));
    }
}
