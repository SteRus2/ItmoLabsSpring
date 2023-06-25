/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.obviously.itmo.prog.gui.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Window;
import us.obviously.itmo.prog.common.model.Person;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.common.server.exceptions.BadRequestException;
import us.obviously.itmo.prog.gui.Main;
import us.obviously.itmo.prog.gui.i18n.Internalization;
import us.obviously.itmo.prog.gui.i18n.Language;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TableController implements Initializable, Translatable {

    private final ExecutorService executorService;
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
    TableColumn<StudyGroup, Person> groupAdminColumn;
    TableColumn<StudyGroup, String> groupAdminNameColumn;
    TableColumn<StudyGroup, java.time.ZonedDateTime> groupAdminBirthdayColumn;
    TableColumn<StudyGroup, Integer> ownerIdColumn;
    TableColumn<StudyGroup, String> ownerUsernameColumn;
    @FXML
    private Text errorMessage;

    @FXML
    private TableView<StudyGroup> tableView;

    public TableController() {
        executorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buildTable();
        executorService.submit(this::loadStudyGroups);
    }

    private void loadStudyGroups() {
        try {
            HashMap<Integer, StudyGroup> groups = Main.manager.getDataCollection().getData();
            List<StudyGroup> groupList = new ArrayList<>(groups.values());
            ObservableList<StudyGroup> observableList = getStudyGroups(groupList);
            tableView.setItems(observableList);
        } catch (BadRequestException e) {
            System.out.println(e.getMessage());
            errorMessage.setText("Ошибка при загрузке информации");
        }
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
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        nameColumn = new TableColumn<>();
        nameColumn.setMinWidth(20);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        coordinatesXColumn = new TableColumn<>();
        coordinatesXColumn.setMinWidth(20);
        coordinatesXColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCoordinates().getX().toString()));

        coordinatesYColumn = new TableColumn<>();
        coordinatesYColumn.setMinWidth(20);
        coordinatesYColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCoordinates().getX().toString()));

        creationDateColumn = new TableColumn<>();
        creationDateColumn.setMinWidth(20);
        creationDateColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate"));

        studentsCountColumn = new TableColumn<>();
        studentsCountColumn.setMinWidth(20);
        studentsCountColumn.setCellValueFactory(new PropertyValueFactory<>("studentsCount"));

        formOfEducationColumn = new TableColumn<>();
        formOfEducationColumn.setMinWidth(20);
        formOfEducationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFormOfEducation().key));

        semesterColumn = new TableColumn<>();
        semesterColumn.setMinWidth(20);
        formOfEducationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSemesterEnum().key));

        groupAdminColumn = new TableColumn<>();
        groupAdminColumn.setMinWidth(20);
        groupAdminColumn.setCellValueFactory(new PropertyValueFactory<>("groupAdmin"));

        groupAdminNameColumn = new TableColumn<>();
        groupAdminColumn.setMinWidth(20);
        coordinatesYColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPerson().getName()));

        groupAdminBirthdayColumn = new TableColumn<>();
        groupAdminColumn.setMinWidth(20);
//        coordinatesYColumn.setCellValueFactory(cellData -> new TableCell<StudyGroup, java.time.ZonedDateTime>() {});

        ownerIdColumn = new TableColumn<>();
        ownerIdColumn.setMinWidth(20);
        ownerIdColumn.setCellValueFactory(new PropertyValueFactory<>("ownerId"));

        ownerUsernameColumn = new TableColumn<>();
        ownerUsernameColumn.setMinWidth(20);
        ownerUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("ownerUsername"));

        tableView.getColumns().addAll(idColumn, nameColumn, coordinatesXColumn, coordinatesYColumn, creationDateColumn, studentsCountColumn, formOfEducationColumn, semesterColumn, groupAdminColumn, ownerIdColumn, ownerUsernameColumn);

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
        groupAdminColumn.setText(Internalization.getTranslation("mainTable.columns.groupAdminColumn"));
        groupAdminNameColumn.setText(Internalization.getTranslation("mainTable.columns.groupAdminNameColumn"));
        groupAdminBirthdayColumn.setText(Internalization.getTranslation("mainTable.columns.groupAdminBirthdayColumn"));
        ownerIdColumn.setText(Internalization.getTranslation("mainTable.columns.ownerIdColumn"));
        ownerUsernameColumn.setText(Internalization.getTranslation("mainTable.columns.ownerUsernameColumn"));
    }
}