package us.obviously.itmo.prog.gui.tools.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import us.obviously.itmo.prog.common.model.Semester;
import us.obviously.itmo.prog.gui.i18n.Language;
import us.obviously.itmo.prog.gui.tools.fields.AbstractField;
import us.obviously.itmo.prog.gui.tools.fields.GroupNameField;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class AddController extends BaseController {

    public GridPane grid;
    private Object apply;
    private List<AbstractField<?, ?>> fieldList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        fieldList = new LinkedList<AbstractField<?, ?>>();

        var groupName = new GroupNameField();
        groupName.addToGrid(grid, 1);
        fieldList.add(groupName);


        Label coordinatesXLabel = new Label("X");
        grid.add(coordinatesXLabel, 0, 2);
        var coordinatesXColumn = new TextField(); // String
        grid.add(coordinatesXColumn, 1, 2);
        coordinatesXColumn.setOnAction(this::onEnter);
        ValidationSupport coordinatesXValidation = new ValidationSupport();
        Validator<String> coordinatesXValidator = (control, value) -> {
//            boolean condition = value == null || value.matches();
            boolean condition = true;
            return ValidationResult.fromMessageIf(control, "not ", Severity.ERROR, condition);
        };
        coordinatesXValidation.registerValidator(coordinatesXColumn, coordinatesXValidator);
//        coordinatesXColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCoordinates().getX().toString()));

        Label coordinatesYLabel = new Label("Y");
        grid.add(coordinatesYLabel, 0, 3);
        var coordinatesYColumn = new TextField(); // String
        grid.add(coordinatesYColumn, 1, 3);
        coordinatesYColumn.setOnAction(this::onEnter);
//        coordinatesYColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCoordinates().getX().toString()));

        Label creationDateLabel = new Label("Creation Date");
        grid.add(creationDateLabel, 0, 4);
        var creationDateColumn = new TextField(); // java
        grid.add(creationDateColumn, 1, 4);
        creationDateColumn.setOnAction(this::onEnter);
//        creationDateColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate"));

        Label studentsCountLabel = new Label("Students Count");
        grid.add(studentsCountLabel, 0, 5);
        var studentsCountColumn = new TextField(); // Integer
        grid.add(studentsCountColumn, 1, 5);
        studentsCountColumn.setOnAction(this::onEnter);
//        studentsCountColumn.setCellValueFactory(new PropertyValueFactory<>("studentsCount"));

        Label formOfEducationLabel = new Label("Form Of Education");
        grid.add(formOfEducationLabel, 0, 6);
        var formOfEducationColumn = new TextField(); // String
        grid.add(formOfEducationColumn, 1, 6);
        formOfEducationColumn.setOnAction(this::onEnter);
//        formOfEducationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFormOfEducation().key));

        Label semesterLabel = new Label("Semester");
        grid.add(semesterLabel, 0, 7);
        var semesterColumn = new TextField(); // String
        grid.add(semesterColumn, 1, 7);
        semesterColumn.setOnAction(this::onEnter);
        ComboBox<Semester> cbxStatus = new ComboBox<>();
        cbxStatus.getItems().setAll(Semester.values());
        ValidationSupport semesterValidation = new ValidationSupport();
//        Validator<String> semesterValidator = new Validator<String>() {
//            @Override
//            public ValidationResult apply(Control control, String value) {
//                boolean condition = value == null || value.matches();
//                return ValidationResult.fromMessageIf(control, "not ", Severity.ERROR, condition);
//            }
//        }
//        semesterValidation.registerValidator(semesterColumn, semesterValidator);
//        formOfEducationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSemesterEnum().key));

        Label groupAdminLabel = new Label("Group Admin");
        grid.add(groupAdminLabel, 0, 8);
        var groupAdminColumn = new TextField(); // Person
        grid.add(groupAdminColumn, 1, 8);
        groupAdminColumn.setOnAction(this::onEnter);
//        groupAdminColumn.setCellValueFactory(new PropertyValueFactory<>("groupAdmin"));

//        var groupAdminColumn = new TextField(GA Name"); // String
//        field.add(groupAdminColumn, 1, 9);
//        groupAdminColumn.setOnAction(this::onEnter);();
//        coordinatesYColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPerson().getName()));

//        var groupAdminColumn = new TextField(GA Name"); // java
//        field.add(groupAdminColumn, 1, 10);
//        groupAdminColumn.setOnAction(this::onEnter);();
//        coordinatesYColumn.setCellValueFactory(cellData -> new TableCell<StudyGroup, java.time.ZonedDateTime>() {});

        Label ownerIdLabel = new Label("Owner ID");
        grid.add(ownerIdLabel, 0, 11);
        var ownerIdColumn = new TextField(); // Integer
        grid.add(ownerIdColumn, 1, 11);
        ownerIdColumn.setOnAction(this::onEnter);
//        ownerIdColumn.setCellValueFactory(new PropertyValueFactory<>("ownerId"));

        Label ownerUsernameLabel = new Label("Owner Username");
        grid.add(ownerUsernameLabel, 0, 12);
        var ownerUsernameColumn = new TextField(); // String
        grid.add(ownerUsernameColumn, 1, 12);
        ownerUsernameColumn.setOnAction(this::onEnter);
//        ownerUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("ownerUsername"));


    }

    @FXML
    private void apply(ActionEvent actionEvent) {

    }

    @FXML
    private void onEnter(ActionEvent actionEvent) {
        this.apply(actionEvent);
    }

    private boolean validate() {
        var dirtyFields = new LinkedList<AbstractField<?, ?>>();
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

    }
}
