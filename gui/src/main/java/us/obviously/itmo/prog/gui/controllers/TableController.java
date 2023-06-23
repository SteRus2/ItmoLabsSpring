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

import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TableController implements Initializable {

    private final ExecutorService executorService;
    @FXML
    public Text infoMessage;
    @FXML
    public Label titleText;
    @FXML
    public VBox sidebar;
    Window window;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button signInButton;
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
        TableColumn<StudyGroup, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(20);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<StudyGroup, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(20);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<StudyGroup, String> coordinatesXColumn = new TableColumn<>("X");
        coordinatesXColumn.setMinWidth(20);
        coordinatesXColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCoordinates().getX().toString()));

        TableColumn<StudyGroup, String> coordinatesYColumn = new TableColumn<>("Y");
        coordinatesYColumn.setMinWidth(20);
        coordinatesYColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCoordinates().getX().toString()));

        TableColumn<StudyGroup, java.util.Date> creationDateColumn = new TableColumn<>("Creation Date");
        creationDateColumn.setMinWidth(20);
        creationDateColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate"));

        TableColumn<StudyGroup, Integer> studentsCountColumn = new TableColumn<>("Students Count");
        studentsCountColumn.setMinWidth(20);
        studentsCountColumn.setCellValueFactory(new PropertyValueFactory<>("studentsCount"));

        TableColumn<StudyGroup, String> formOfEducationColumn = new TableColumn<>("Form Of Education");
        formOfEducationColumn.setMinWidth(20);
        formOfEducationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFormOfEducation().key));

        TableColumn<StudyGroup, String> semesterColumn = new TableColumn<>("Semester");
        semesterColumn.setMinWidth(20);
        formOfEducationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSemesterEnum().key));

        TableColumn<StudyGroup, Person> groupAdminColumn = new TableColumn<>("Group Admin");
        groupAdminColumn.setMinWidth(20);
        groupAdminColumn.setCellValueFactory(new PropertyValueFactory<>("groupAdmin"));

        TableColumn<StudyGroup, String> groupAdminNameColumn = new TableColumn<>("GA Name");
        groupAdminColumn.setMinWidth(20);
        coordinatesYColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPerson().getName()));

        TableColumn<StudyGroup, java.time.ZonedDateTime> groupAdminBirthdayColumn = new TableColumn<>("GA Name");
        groupAdminColumn.setMinWidth(20);
//        coordinatesYColumn.setCellValueFactory(cellData -> new TableCell<StudyGroup, java.time.ZonedDateTime>() {});

        TableColumn<StudyGroup, Integer> ownerIdColumn = new TableColumn<>("Owner ID");
        ownerIdColumn.setMinWidth(20);
        ownerIdColumn.setCellValueFactory(new PropertyValueFactory<>("ownerId"));

        TableColumn<StudyGroup, String> ownerUsernameColumn = new TableColumn<>("Owner Username");
        ownerUsernameColumn.setMinWidth(20);
        ownerUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("ownerUsername"));

        tableView.getColumns().addAll(idColumn, nameColumn, coordinatesXColumn, coordinatesYColumn, creationDateColumn, studentsCountColumn, formOfEducationColumn, semesterColumn, groupAdminColumn, ownerIdColumn, ownerUsernameColumn);
    }

    private ObservableList<StudyGroup> getStudyGroups(List<StudyGroup> list) {
        return FXCollections.observableList(list);
    }

}