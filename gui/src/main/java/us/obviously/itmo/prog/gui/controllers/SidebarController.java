package us.obviously.itmo.prog.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import us.obviously.itmo.prog.gui.Main;
import us.obviously.itmo.prog.gui.tools.AbstractTool;
import us.obviously.itmo.prog.gui.views.ViewsManager;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class SidebarController implements Initializable {
    @FXML
    public Text usernameText;
    @FXML
    public Button logoutButton;
    @FXML
    public VBox commandBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var commands = new LinkedList<AbstractTool>();
        commands.add(new AbstractTool("Добавить", event -> {
            Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            try {
                ViewsManager.showAddToolView(stage);
            } catch (IOException e) {
                // error
            }
        }));
        commands.add(new AbstractTool("Удалить", event -> {
            Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            try {
                ViewsManager.showDeleteToolView(stage);
            } catch (IOException e) {
                // error
            }
        }));
        commands.add(new AbstractTool("Заменить", event -> {
            Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            try {
                ViewsManager.showReplaceToolView(stage);
            } catch (IOException e) {
                // error
            }
        }));
        commands.add(new AbstractTool("Очистить", event -> {
            Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            try {
                ViewsManager.showClearToolView(stage);
            } catch (IOException e) {
                // error
            }
        }));
        commands.add(new AbstractTool("Исполнить скрипт", event -> {
            Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            try {
                ViewsManager.showExecuteScriptToolView(stage);
            } catch (IOException e) {
                // error
            }
        }));
        commands.add(new AbstractTool("Группировка по имени", event -> {
            Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            try {
                ViewsManager.showGroupByNameToolView(stage);
            } catch (IOException e) {
                // error
            }
        }));
        commands.add(new AbstractTool("Служебная информация", event -> {
            Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            try {
                ViewsManager.showInfoToolView(stage);
            } catch (IOException e) {
                // error
            }
        }));
        commands.add(new AbstractTool("Фильтр по админу группы", event -> {
            Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            try {
                ViewsManager.showFilterByGroupAdminToolView(stage);
            } catch (IOException e) {
                // error
            }
        }));
        commands.add(new AbstractTool("Значения семестров", event -> {
            Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            try {
                ViewsManager.showSemesterValuesToolView(stage);
            } catch (IOException e) {
                // error
            }
        }));
        commands.forEach((value) -> {
            var button = new Button(value.displayName());
            button.setOnAction(value.event());
            commandBox.getChildren().add(button);
        });

        usernameText.setText(Main.client.getLogin());
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
}
