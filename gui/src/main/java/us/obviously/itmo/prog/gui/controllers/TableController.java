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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import us.obviously.itmo.prog.common.model.Semester;
import us.obviously.itmo.prog.gui.Main;
import us.obviously.itmo.prog.gui.i18n.Internalization;
import us.obviously.itmo.prog.gui.i18n.Language;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class TableController implements Initializable, Translatable {

    @FXML
    public Text infoMessage;
    @FXML
    public Label titleText;
    @FXML
    public VBox sidebar;
    public Pane studyGroupTable;
    public Pane studyGroupTableGroupByName;
    public Pane semesterTable;
    public ComboBox<TableViewEnum> tableComboBox;
    @FXML
    private Text errorMessage;

    public TableController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<TableViewEnum> list = new ArrayList<>(List.of(new TableViewEnum[]{TableViewEnum.STUDY_GROUP, TableViewEnum.STUDY_GROUP_GROUP_BY_NAME, TableViewEnum.SEMESTER}));
        tableComboBox.setConverter(new StringConverter<TableViewEnum>() {
            @Override
            public String toString(TableViewEnum object) {
                return Internalization.getTranslation(object.key);
            }

            @Override
            public TableViewEnum fromString(String string) {
                return null;
            }
        });
        tableComboBox.setItems(FXCollections.observableList(list));
        tableComboBox.setOnAction(event -> {
            var value = tableComboBox.getValue();
            studyGroupTable.setManaged(false);
            studyGroupTableGroupByName.setManaged(false);
            semesterTable.setManaged(false);
            studyGroupTable.setVisible(false);
            studyGroupTableGroupByName.setVisible(false);
            semesterTable.setVisible(false) ;
            Main.viewTable = value;
            switch (value) {
                case STUDY_GROUP -> {
                    studyGroupTable.setManaged(true);
                    studyGroupTable.setVisible(true);
                }
                case STUDY_GROUP_GROUP_BY_NAME -> {
                    studyGroupTableGroupByName.setManaged(true);
                    studyGroupTableGroupByName.setVisible(true);
                }
                case SEMESTER -> {
                    semesterTable.setManaged(true);
                    semesterTable.setVisible(true);
                }
            }
        });
        tableComboBox.setValue(Main.viewTable);
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


    private ObservableList<Semester> getSemesters(List<Semester> list) {
        return FXCollections.observableList(list);
    }

    @Override
    public void setBundle(Language language) {

    }

}