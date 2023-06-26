/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.obviously.itmo.prog.gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import us.obviously.itmo.prog.client.RequestManager;
import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.common.server.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.server.exceptions.FailedToReadRemoteException;
import us.obviously.itmo.prog.gui.Main;
import us.obviously.itmo.prog.gui.StudyGroupTable;
import us.obviously.itmo.prog.gui.i18n.Language;
import us.obviously.itmo.prog.gui.views.ViewsManager;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class StudyGroupTableController implements Initializable, Translatable {

    private final ExecutorService executorService;
    private final ExecutorService listenUpdatesExecutorService;
    @FXML
    public Text infoMessage;
    @FXML
    public Label titleText;
    @FXML
    public VBox sidebar;
    @FXML
    private Text errorMessage;

    @FXML
    private TableView<StudyGroup> tableView;
    private StudyGroupTable table;

    public StudyGroupTableController() {
        executorService = Executors.newSingleThreadExecutor();
        listenUpdatesExecutorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            table = new StudyGroupTable(tableView);
            tableView.setRowFactory(tv -> {

                TableRow<StudyGroup> row = new TableRow<>();

                row.setOnMouseClicked(event -> {
                    if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                            && event.getClickCount() == 2) {

                        StudyGroup clickedRow = row.getItem();
                        if (clickedRow.getOwnerId() != Main.client.getId()) {
                            return;
                        }
                        var stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        try {
                            ViewsManager.showUpdateToolView(stage, clickedRow);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                return row;
            });

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
                table.updateStudyGroups(groups);
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
            table.updateStudyGroups(groups);
            sleep(2000);
            loadStudyGroups();
        } catch (BadRequestException e) {
            System.out.println(e.getMessage());
            errorMessage.setText("Ошибка при загрузке информации"); // TODO: translate
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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

    private ObservableList<StudyGroup> getStudyGroups(List<StudyGroup> list) {
        return FXCollections.observableList(list);
    }

    @Override
    public void setBundle(Language language) {
        table.setBundle(language);
    }

}