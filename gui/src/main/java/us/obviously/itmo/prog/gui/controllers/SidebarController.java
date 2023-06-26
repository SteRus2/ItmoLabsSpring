package us.obviously.itmo.prog.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import us.obviously.itmo.prog.common.server.exceptions.ExecuteCommandException;
import us.obviously.itmo.prog.common.server.exceptions.RecurrentExecuteScripts;
import us.obviously.itmo.prog.gui.FilesManager;
import us.obviously.itmo.prog.gui.Main;
import us.obviously.itmo.prog.gui.i18n.Internalization;
import us.obviously.itmo.prog.gui.i18n.Language;
import us.obviously.itmo.prog.gui.notifications.Notifications;
import us.obviously.itmo.prog.gui.tools.AbstractTool;
import us.obviously.itmo.prog.gui.views.ViewsManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class SidebarController implements Initializable, Translatable {
    @FXML
    public Text usernameText;
    @FXML
    public Button logoutButton;
    @FXML
    public VBox commandBox;
    private LinkedList<AbstractTool> commands;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commands = new LinkedList<AbstractTool>();
        commands.add(new AbstractTool("add", event -> {
            Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            try {
                ViewsManager.showAddToolView(stage);
            } catch (IOException e) {
                // error
            }
        }));
        commands.add(new AbstractTool("delete", event -> {
            Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            try {
                ViewsManager.showRemoveToolView(stage);
            } catch (IOException e) {
                // error
            }
        }));
        commands.add(new AbstractTool("replace", event -> {
            Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            try {
                ViewsManager.showReplaceToolView(stage);
            } catch (IOException e) {
                // error
            }
        }));
        commands.add(new AbstractTool("clear", event -> {
            Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            try {
                ViewsManager.showClearToolView(stage);
            } catch (IOException e) {
                // error
            }
        }));
        commands.add(new AbstractTool("executeScript", event -> {
            Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            try {
                FilesManager.executeScript(stage);
                Notifications.showAlert(Alert.AlertType.INFORMATION, stage, "Успешно!", "Скрипт успешно выполнен"); // TODO: translate
            } catch (FileNotFoundException e) {
                // error
            } catch (RecurrentExecuteScripts e) {
                throw new RuntimeException(e);
            } catch (ExecuteCommandException e) {
                throw new RuntimeException(e);
            }
        }));
        commands.add(new AbstractTool("groupByName", event -> {
            Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            try {
                ViewsManager.showGroupByNameToolView(stage);
            } catch (IOException e) {
                // error
            }
        }));
        commands.add(new AbstractTool("serviceInformation", event -> {
            Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            try {
                ViewsManager.showInfoToolView(stage);
            } catch (IOException e) {
                // error
            }
        }));
        commands.add(new AbstractTool("filterByGroupAdmin", event -> {
            Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            try {
                ViewsManager.showFilterByGroupAdminToolView(stage);
            } catch (IOException e) {
                // error
            }
        }));
        commands.add(new AbstractTool("meaningsOfSemesters", event -> {
            Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            try {
                ViewsManager.showSemesterValuesToolView(stage);
            } catch (IOException e) {
                // error
            }
        }));
        commands.forEach((value) -> {
            commandBox.getChildren().add(value.button);
        });

        usernameText.setText(Main.client.getLogin());

        setBundle(Language.RUSSIAN);

        Internalization.addTranslatable(this);
    }

    @FXML
    public void signOut(ActionEvent actionEvent) {
        var stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        try {
            ViewsManager.showSignInView(stage);
        } catch (IOException e) {
            // TODO: выводить сообщение
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setBundle(Language language) {
        // TODO: не вызывается
        for (AbstractTool command : commands) {
            command.updateText();
        }
        logoutButton.setText(Internalization.getTranslation("sidebar.logout"));
    }
}
