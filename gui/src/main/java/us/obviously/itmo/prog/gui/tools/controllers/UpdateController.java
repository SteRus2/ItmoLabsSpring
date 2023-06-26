package us.obviously.itmo.prog.gui.tools.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import us.obviously.itmo.prog.common.model.Coordinates;
import us.obviously.itmo.prog.common.model.Person;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.common.server.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.server.exceptions.NoSuchIdException;
import us.obviously.itmo.prog.gui.Main;
import us.obviously.itmo.prog.gui.i18n.Internalization;
import us.obviously.itmo.prog.gui.i18n.Language;
import us.obviously.itmo.prog.gui.notifications.Notifications;
import us.obviously.itmo.prog.gui.tools.fields.*;
import us.obviously.itmo.prog.gui.views.ViewsManager;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateController extends BaseController {

    public GridPane grid;
    public Button submitButton;
    public Button cancelButton;
    public Button deleteButton;
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
    private StudyGroup currentStudyGroup;
    private CheckBox replaceIfGreater;

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
            semesterField.addToGrid(grid, currentIndex++);
            ;
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

            replaceIfGreater = new CheckBox("field.replaceIfGreater");
            grid.add(replaceIfGreater, 0, currentIndex);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }

    private void applyValue(StudyGroup studyGroup) {
        groupNameField.setValue(studyGroup.getName());
        coordinatesXField.setValue(studyGroup.getCoordinates().getX());
        coordinatesYField.setValue(studyGroup.getCoordinates().getY());
        studentsCountField.setValue(studyGroup.getStudentsCount());
        formOfEducationField.setValue(studyGroup.getFormOfEducation());
        semesterField.setValue(studyGroup.getSemesterEnum());
        adminNameField.setValue(studyGroup.getGroupAdmin().getName());
        birthdayField.setValue(studyGroup.getGroupAdmin().getBirthday());
        eyeColorField.setValue(studyGroup.getGroupAdmin().getEyeColor());
        hairColorField.setValue(studyGroup.getGroupAdmin().getHairColor());
        nationalityField.setValue(studyGroup.getGroupAdmin().getNationality());
    }

    public void setStudyGroup(StudyGroup studyGroup) {
//        var studyGroup = Main.currentStudyGroups.get(id);
        if (studyGroup == null || studyGroup.getId() == null) {
            throw new RuntimeException("ТАКОГО БЫТЬ НЕ ДОЛЖНО");
        }
        this.currentStudyGroup = studyGroup;
        this.applyValue(studyGroup);
    }

    @FXML
    private void apply(ActionEvent actionEvent) {
        if (this.currentStudyGroup == null || this.currentStudyGroup.getId() == null) {
            throw new RuntimeException("ТАКОГО АФА");
        }
        if (this.validate()) {
            var groupId = this.currentStudyGroup.getId();
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
            StudyGroup studyGroup = new StudyGroup(groupId, groupName, coordinates, dateNow, studentsCount, formOfEducation, semester, person);
            try {
                if (replaceIfGreater.isSelected()) {
                    Main.manager.getDataCollection().replaceIfGreater(studyGroup, groupId);
                } else {
                    Main.manager.getDataCollection().updateItem(studyGroup, groupId);
                }
                errorText.setText("FINE)) " + groupId);
            } catch (BadRequestException e) {
                errorText.setText("BAD RQEUS" + e.getMessage());
            } catch (NoSuchIdException e) {
                throw new RuntimeException(e);
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
        submitButton.setText(Internalization.getTranslation("tool.update.submit"));
        cancelButton.setText(Internalization.getTranslation("tool.update.cancel"));
        deleteButton.setText(Internalization.getTranslation("tool.update.delete"));
        replaceIfGreater.setText(Internalization.getTranslation("field.replaceIfGreater"));
    }

    public void delete(ActionEvent actionEvent) {
        if (this.currentStudyGroup == null || this.currentStudyGroup.getId() == null) {
            throw new RuntimeException("ТАКОГО АФА");
        }
        try {
            Main.manager.getDataCollection().removeItem(this.currentStudyGroup.getId());
            errorText.setText("FINE)) ");
            var stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Platform.runLater(() -> {
                var title = Internalization.getTranslation("tool.update.delete.success.title");
                var message = Internalization.getTranslation("tool.update.delete.success.message");
                Notifications.showAlert(Alert.AlertType.INFORMATION, stage, title, message);
            });
            ViewsManager.showTableView(stage);
        } catch (NoSuchIdException e) {
            throw new RuntimeException(e);
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
