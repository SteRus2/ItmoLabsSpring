/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.obviously.itmo.prog.gui.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import us.obviously.itmo.prog.client.RequestManager;
import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.common.server.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.server.exceptions.FailedToReadRemoteException;
import us.obviously.itmo.prog.gui.Main;
import us.obviously.itmo.prog.gui.i18n.Internalization;
import us.obviously.itmo.prog.gui.i18n.Language;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StudyGroupTableController implements Initializable, Translatable {

    private final ExecutorService executorService;
    private final ExecutorService listenUpdatesExecutorService;
    @FXML
    public Text infoMessage;
    @FXML
    public Label titleText;
    @FXML
    public VBox sidebar;
    TableColumn<StudyGroup, Integer> idColumn;
    TableColumn<StudyGroup, String> nameColumn;
    TableColumn<StudyGroup, String> coordinatesXColumn;
    TableColumn<StudyGroup, String> coordinatesYColumn;
    TableColumn<StudyGroup, java.util.Date> creationDateColumn;
    TableColumn<StudyGroup, Integer> studentsCountColumn;
    TableColumn<StudyGroup, String> formOfEducationColumn;
    TableColumn<StudyGroup, String> semesterColumn;
    TableColumn<StudyGroup, String> groupAdminNameColumn;
    TableColumn<StudyGroup, ZonedDateTime> groupAdminBirthdayColumn;
    TableColumn<StudyGroup, String> groupAdminEyeColorColumn;
    TableColumn<StudyGroup, String> groupAdminHairColorColumn;
    TableColumn<StudyGroup, String> groupAdminNationalityColumn;
    TableColumn<StudyGroup, Integer> ownerIdColumn;
    TableColumn<StudyGroup, String> ownerUsernameColumn;
    @FXML
    private Text errorMessage;

    @FXML
    private TableView<StudyGroup> tableView;

    public StudyGroupTableController() {
        executorService = Executors.newSingleThreadExecutor();
        listenUpdatesExecutorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            buildTable();
            executorService.submit(this::loadStudyGroups);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
//        listenUpdatesExecutorService.submit(this::listenUpdates);
    }

    private void listenUpdates() {
//        Main.manager.getDataCollection().
//        Main.manager.sendStudyGroupsTableListenRequest();
        var requestManager = new RequestManager<VoidModel, HashMap<Integer, StudyGroup>>();
        while (true) {
            try {
                Map<Integer, StudyGroup> groups = requestManager.receive(Main.client);
                updateStudyGroups(groups);
            } catch (BadRequestException e) {
                System.out.println(e.getMessage());
                errorMessage.setText("Ошибка при загрузке информации"); // TODO: replace with internalize
                break;
            } catch (FailedToReadRemoteException e) {
                System.out.println(e.getMessage());
                errorMessage.setText("Ошибка при загрузке информации"); // TODO: replace with internalize
                break;
            }
        }
//        Main.manager.sendStudyGroupsTableUnattachRequest();
    }

    private void loadStudyGroups() {
        try {
            Map<Integer, StudyGroup> groups = Main.manager.getDataCollection().getData();
            updateStudyGroups(groups);
        } catch (BadRequestException e) {
            System.out.println(e.getMessage());
            errorMessage.setText("Ошибка при загрузке информации");
        }
    }

    private void updateStudyGroups(Map<Integer, StudyGroup> groups) {
        Main.currentStudyGroups = groups;
        List<StudyGroup> groupList = new ArrayList<>(groups.values());
        ObservableList<StudyGroup> observableList = getStudyGroups(groupList);
        tableView.setItems(observableList);
    }

    private void showInfoMessage(String message) {
        infoMessage.setVisible(true);
        infoMessage.setText(message);
    }

    private void hideInfoMessage() {
        infoMessage.setVisible(false);
    }

    private void showErrorMessage(String message) {
        errorMessage.setVisible(true);
        errorMessage.setText(message);
    }

    private void hideErrorMessage() {
        errorMessage.setVisible(false);
    }

    public void buildTable() {
        idColumn = new TableColumn<>();
        idColumn.setMinWidth(20);
        idColumn.setPrefWidth(20);
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
            protected void updateItem(java.util.Date item, boolean empty) {
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

//        tableView.getColumns().addAll(idColumn, nameColumn, coordinatesXColumn, coordinatesYColumn, creationDateColumn, studentsCountColumn, formOfEducationColumn, semesterColumn, groupAdminNameColumn, groupAdminBirthdayColumn, groupAdminEyeColorColumn, groupAdminHairColorColumn, groupAdminNationalityColumn, ownerIdColumn, ownerUsernameColumn);

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