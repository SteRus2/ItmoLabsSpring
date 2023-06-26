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

public class ReadController extends BaseController {

    public GridPane grid;
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
    private StudyGroup currentStudyGroup;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(25, 25, 25, 25));

            fieldList = new LinkedList<>();

            int currentIndex = 0;

            groupNameField = new GroupNameField();
            groupNameField.getControl().setDisable(true);
            groupNameField.addToGrid(grid, currentIndex++);
            fieldList.add(groupNameField);

            coordinatesXField = new CoordinatesXField();
            coordinatesXField.getControl().setDisable(true);
            coordinatesXField.addToGrid(grid, currentIndex++);
            fieldList.add(coordinatesXField);

            coordinatesYField = new CoordinatesYField();
            coordinatesYField.getControl().setDisable(true);
            coordinatesYField.addToGrid(grid, currentIndex++);
            fieldList.add(coordinatesYField);

            studentsCountField = new StudentsCountField();
            studentsCountField.getControl().setDisable(true);
            studentsCountField.addToGrid(grid, currentIndex++);
            fieldList.add(studentsCountField);

            formOfEducationField = new FormOfEducationField();
            formOfEducationField.getControl().setDisable(true);
            formOfEducationField.addToGrid(grid, currentIndex++);
            fieldList.add(formOfEducationField);

            semesterField = new SemesterField();
            semesterField.getControl().setDisable(true);
            semesterField.addToGrid(grid, currentIndex++);
            ;
            fieldList.add(semesterField);

            /* ADMIN FIELDS */

            adminNameField = new AdminNameField();
            adminNameField.getControl().setDisable(true);
            adminNameField.addToGrid(grid, currentIndex++);
            fieldList.add(adminNameField);

            birthdayField = new AdminBirthdayField();
            birthdayField.getControl().setDisable(true);
            birthdayField.addToGrid(grid, currentIndex++);
            fieldList.add(birthdayField);

            eyeColorField = new AdminEyeColorField();
            eyeColorField.getControl().setDisable(true);
            eyeColorField.addToGrid(grid, currentIndex++);
            fieldList.add(eyeColorField);

            hairColorField = new AdminHairColorField();
            hairColorField.getControl().setDisable(true);
            hairColorField.addToGrid(grid, currentIndex++);
            fieldList.add(hairColorField);

            nationalityField = new AdminNationalityField();
            nationalityField.getControl().setDisable(true);
            nationalityField.addToGrid(grid, currentIndex++);
            fieldList.add(nationalityField);

            /* ADMIN FIELDS FINISH */
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
        if (studyGroup == null || studyGroup.getId() == null) {
            throw new RuntimeException("ТАКОГО БЫТЬ НЕ ДОЛЖНО");
        }
        this.currentStudyGroup = studyGroup;
        this.applyValue(studyGroup);
    }

    @Override
    public void setBundle(Language language) {
        cancelButton.setText(Internalization.getTranslation("tool.read.cancel"));
    }
}
