/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.obviously.itmo.prog.gui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import us.obviously.itmo.prog.client.RequestManager;
import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.common.server.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.server.exceptions.FailedToReadRemoteException;
import us.obviously.itmo.prog.gui.Main;
import us.obviously.itmo.prog.gui.StudyGroupTable;
import us.obviously.itmo.prog.gui.i18n.Language;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StudyGroupTableGroupByNameController implements Initializable, Translatable {

    private final ExecutorService executorService;
    private final ExecutorService listenUpdatesExecutorService;
    @FXML
    private final List<TableView<StudyGroup>> tableViews = new ArrayList<>();
    private final List<StudyGroupTable> tables = new ArrayList<>();
    @FXML
    public Text infoMessage;
    @FXML
    public Label titleText;
    @FXML
    public VBox sidebar;
    public Pane tableViewsLayout;
    @FXML
    private Text errorMessage;

    public StudyGroupTableGroupByNameController() {
        executorService = Executors.newSingleThreadExecutor();
        listenUpdatesExecutorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            executorService.submit(this::loadStudyGroups);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
//        listenUpdatesExecutorService.submit(this::listenUpdates);
    }

    private void listenUpdates() {
//        Main.manager.getDataCollection().
//        Main.manager.sendStudyGroupsTableListenRequest();
        var requestManager = new RequestManager<VoidModel, Map<String, List<StudyGroup>>>();
        while (true) {
            try {
                Map<String, List<StudyGroup>> groupsMap = requestManager.receive(Main.client);
                groupsMap.forEach((key, groups) -> {
                    var tableView = new TableView<StudyGroup>();
//                    tableView.row
                    tableViews.add(tableView);
                    var table = new StudyGroupTable(tableView);
                    table.updateStudyGroups(groups);
                });
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
            Map<String, List<StudyGroup>> groupsMap = Main.manager.getDataCollection().groupCountingByName();
            Platform.runLater(() -> {
                try {
                    groupsMap.forEach((key, groups) -> {
                        var tableView = new TableView<StudyGroup>();
                        tableViews.add(tableView);
                        var table = new StudyGroupTable(tableView);
                        table.updateStudyGroups(groups);
                        tableViewsLayout.getChildren().add(tableView);
                    });
//                tableViews.forEach(tableView ->
//                            tableViewsLayout.getChildren().add(tableView);
//                );
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                }
            });
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

    @Override
    public void setBundle(Language language) {
        tables.forEach(table -> setBundle(language));
    }
}