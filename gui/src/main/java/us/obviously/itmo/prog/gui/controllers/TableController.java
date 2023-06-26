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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import us.obviously.itmo.prog.client.RequestManager;
import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.model.Semester;
import us.obviously.itmo.prog.common.server.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.server.exceptions.FailedToReadRemoteException;
import us.obviously.itmo.prog.gui.Main;
import us.obviously.itmo.prog.gui.i18n.Internalization;
import us.obviously.itmo.prog.gui.i18n.Language;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TableController implements Initializable, Translatable {

    @FXML
    public Text infoMessage;
    @FXML
    public Label titleText;
    @FXML
    public VBox sidebar;
    TableColumn<Semester, String> semesterColumn;
    @FXML
    private Text errorMessage;

    public TableController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            buildTable();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
//        listenUpdatesExecutorService.submit(this::listenUpdates);
    }

    private void listenUpdates() {
//        Main.manager.getDataCollection().
//        Main.manager.sendStudyGroupsTableListenRequest();
        var requestManager = new RequestManager<VoidModel, List<Semester>>();
        while (true) {
            try {
                List<Semester> semesters = requestManager.receive(Main.client);
                updateStudyGroups(semesters);
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
            List<Semester> semesters = Main.manager.getDataCollection().printFieldAscendingSemesterEnum();
            updateStudyGroups(semesters);
        } catch (BadRequestException e) {
            System.out.println(e.getMessage());
            errorMessage.setText("Ошибка при загрузке информации");
        }
    }

    private void updateStudyGroups(List<Semester> semesters) {
        ObservableList<Semester> observableList = getSemesters(semesters);
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

    }

    private ObservableList<Semester> getSemesters(List<Semester> list) {
        return FXCollections.observableList(list);
    }

    @Override
    public void setBundle(Language language) {
        displayColumnTitles();
    }

    private void displayColumnTitles() {
        semesterColumn.setText(Internalization.getTranslation("mainTable.columns.semesterColumn"));
    }
}