package us.obviously.itmo.prog.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.gui.controllers.Translatable;
import us.obviously.itmo.prog.gui.i18n.Internalization;
import us.obviously.itmo.prog.gui.i18n.Language;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class StudyGroupTable implements Translatable {

    private final TableView<StudyGroup> tableView;
    private TableColumn<StudyGroup, Integer> idColumn;
    private TableColumn<StudyGroup, String> nameColumn;
    private TableColumn<StudyGroup, String> coordinatesXColumn;
    private TableColumn<StudyGroup, String> coordinatesYColumn;
    private TableColumn<StudyGroup, Date> creationDateColumn;
    private TableColumn<StudyGroup, Integer> studentsCountColumn;
    private TableColumn<StudyGroup, String> formOfEducationColumn;
    private TableColumn<StudyGroup, String> semesterColumn;
    private TableColumn<StudyGroup, String> groupAdminNameColumn;
    private TableColumn<StudyGroup, ZonedDateTime> groupAdminBirthdayColumn;
    private TableColumn<StudyGroup, String> groupAdminEyeColorColumn;
    private TableColumn<StudyGroup, String> groupAdminHairColorColumn;
    private TableColumn<StudyGroup, String> groupAdminNationalityColumn;
    private TableColumn<StudyGroup, Integer> ownerIdColumn;
    private TableColumn<StudyGroup, String> ownerUsernameColumn;

    public StudyGroupTable(TableView<StudyGroup> tableView) {
        this.tableView = tableView;
        buildTable();
    }

    public void updateStudyGroups(List<StudyGroup> groupList) {
        ObservableList<StudyGroup> observableList = getStudyGroups(groupList);
        tableView.setItems(observableList);
    }

    public void updateStudyGroups(Map<Integer, StudyGroup> groups) {
        Main.currentStudyGroups = groups;
        List<StudyGroup> groupList = new ArrayList<>(groups.values());
        ObservableList<StudyGroup> observableList = getStudyGroups(groupList);
        tableView.setItems(observableList);
    }

    public void buildTable() {
        idColumn = new TableColumn<>();
        idColumn.setMinWidth(20);
        idColumn.setPrefWidth(40);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        nameColumn = new TableColumn<>();
        nameColumn.setMinWidth(20);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        coordinatesXColumn = new TableColumn<>();
        coordinatesXColumn.setMinWidth(20);
        coordinatesXColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCoordinates().getX().toString()));

        coordinatesYColumn = new TableColumn<>();
        coordinatesYColumn.setMinWidth(20);
        coordinatesYColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCoordinates().getY().toString()));

        creationDateColumn = new TableColumn<>();
        creationDateColumn.setMinWidth(20);
        creationDateColumn.setCellFactory(column -> new TableCell<>() {
            private final SimpleDateFormat format = new SimpleDateFormat(Internalization.getTranslation("formats.date"));

            @Override
            protected void updateItem(Date item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(format.format(item));
                }
            }
        });
        creationDateColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate"));

        studentsCountColumn = new TableColumn<>();
        studentsCountColumn.setMinWidth(20);
        studentsCountColumn.setCellValueFactory(new PropertyValueFactory<>("studentsCount"));

        formOfEducationColumn = new TableColumn<>();
        formOfEducationColumn.setMinWidth(20);
        formOfEducationColumn.setCellValueFactory(cellData -> {
            var value = cellData.getValue().getFormOfEducation();
            String key = "formOfEducation.null";
            if (value != null) key = value.key;
            return new SimpleStringProperty(Internalization.getTranslation(key));
        });

        semesterColumn = new TableColumn<>();
        semesterColumn.setMinWidth(20);
        semesterColumn.setCellValueFactory(cellData -> {
            var value = cellData.getValue().getSemesterEnum();
            String key = "semester.null";
            if (value != null) key = value.key;
            return new SimpleStringProperty(Internalization.getTranslation(key));
        });

        /* GROUP ADMIN */

        groupAdminNameColumn = new TableColumn<>();
        groupAdminNameColumn.setMinWidth(20);
        groupAdminNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPerson().getName()));

        groupAdminBirthdayColumn = new TableColumn<>();
        groupAdminBirthdayColumn.setMinWidth(20);
        groupAdminBirthdayColumn.setCellFactory(column -> new TableCell<>() {

            private final DateTimeFormatter format = DateTimeFormatter.ofPattern(Internalization.getTranslation("formats.zonedDate"));

            @Override
            protected void updateItem(ZonedDateTime item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(format.format(item));
                }
            }
        });
        groupAdminBirthdayColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getGroupAdmin().getBirthday()));

        groupAdminEyeColorColumn = new TableColumn<>();
        groupAdminEyeColorColumn.setMinWidth(20);
        groupAdminEyeColorColumn.setCellValueFactory(cellData -> {
            var value = cellData.getValue().getPerson().getEyeColor();
            String key = "eyeColor.null";
            if (value != null) key = value.key;
            return new SimpleStringProperty(Internalization.getTranslation(key));
        });

        groupAdminHairColorColumn = new TableColumn<>();
        groupAdminHairColorColumn.setMinWidth(20);
        groupAdminHairColorColumn.setCellValueFactory(cellData -> {
            var value = cellData.getValue().getPerson().getHairColor();
            String key = "hairColor.null";
            if (value != null) key = value.key;
            return new SimpleStringProperty(Internalization.getTranslation(key));
        });

        groupAdminNationalityColumn = new TableColumn<>();
        groupAdminNationalityColumn.setMinWidth(20);
        groupAdminNationalityColumn.setCellValueFactory(cellData -> {
            var value = cellData.getValue().getPerson().getNationality();
            String key = "nationality.null";
            if (value != null) key = value.key;
            return new SimpleStringProperty(Internalization.getTranslation(key));
        });

        /* GROUP ADMIN FINISH */


        /* OWNER */

        ownerIdColumn = new TableColumn<>();
        ownerIdColumn.setMinWidth(20);
        ownerIdColumn.setCellValueFactory(new PropertyValueFactory<>("ownerId"));

        ownerUsernameColumn = new TableColumn<>();
        ownerUsernameColumn.setMinWidth(20);
        ownerUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("ownerUsername"));

        /* OWNER FINISH */

        tableView.getColumns().add(idColumn);
        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(coordinatesXColumn);
        tableView.getColumns().add(coordinatesYColumn);
        tableView.getColumns().add(creationDateColumn);
        tableView.getColumns().add(studentsCountColumn);
        tableView.getColumns().add(formOfEducationColumn);
        tableView.getColumns().add(semesterColumn);
        tableView.getColumns().add(groupAdminNameColumn);
        tableView.getColumns().add(groupAdminBirthdayColumn);
        tableView.getColumns().add(groupAdminEyeColorColumn);
        tableView.getColumns().add(groupAdminHairColorColumn);
        tableView.getColumns().add(groupAdminNationalityColumn);
        tableView.getColumns().add(ownerIdColumn);
        tableView.getColumns().add(ownerUsernameColumn);

        displayColumnTitles();
    }

    private ObservableList<StudyGroup> getStudyGroups(List<StudyGroup> list) {
        return FXCollections.observableList(list);
    }

    @Override
    public void setBundle(Language language) {
        displayColumnTitles();
    }

    private void displayColumnTitles() {
        idColumn.setText(Internalization.getTranslation("mainTable.columns.idColumn"));
        nameColumn.setText(Internalization.getTranslation("mainTable.columns.nameColumn"));
        coordinatesXColumn.setText(Internalization.getTranslation("mainTable.columns.coordinatesXColumn"));
        coordinatesYColumn.setText(Internalization.getTranslation("mainTable.columns.coordinatesYColumn"));
        creationDateColumn.setText(Internalization.getTranslation("mainTable.columns.creationDateColumn"));
        studentsCountColumn.setText(Internalization.getTranslation("mainTable.columns.studentsCountColumn"));
        formOfEducationColumn.setText(Internalization.getTranslation("mainTable.columns.formOfEducationColumn"));
        semesterColumn.setText(Internalization.getTranslation("mainTable.columns.semesterColumn"));
        groupAdminNameColumn.setText(Internalization.getTranslation("mainTable.columns.groupAdminNameColumn"));
        groupAdminBirthdayColumn.setText(Internalization.getTranslation("mainTable.columns.groupAdminBirthdayColumn"));
        groupAdminEyeColorColumn.setText(Internalization.getTranslation("mainTable.columns.groupAdminEyeColorColumn"));
        groupAdminHairColorColumn.setText(Internalization.getTranslation("mainTable.columns.groupAdminHairColorColumn"));
        groupAdminNationalityColumn.setText(Internalization.getTranslation("mainTable.columns.groupAdminNationalityColumn"));
        ownerIdColumn.setText(Internalization.getTranslation("mainTable.columns.ownerIdColumn"));
        ownerUsernameColumn.setText(Internalization.getTranslation("mainTable.columns.ownerUsernameColumn"));
    }
}