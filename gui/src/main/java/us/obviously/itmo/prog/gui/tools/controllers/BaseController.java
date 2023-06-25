package us.obviously.itmo.prog.gui.tools.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import us.obviously.itmo.prog.gui.controllers.Translatable;
import us.obviously.itmo.prog.gui.views.ViewsManager;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class BaseController implements Initializable, Translatable {

    protected final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @FXML
    public Text errorText;
    @FXML
    public Text infoText;

    public void close(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            ViewsManager.showTableView(stage);
        } catch (IOException e) {
            showErrorText(e.getMessage());
        }
    }

    public void showInfoText(String message) {
        errorText.setManaged(false);
        errorText.setVisible(false);
        infoText.setManaged(true);
        infoText.setVisible(true);
        infoText.setText(message);
    }

    public void hideInfoText() {
        infoText.setVisible(false);
    }

    public void showErrorText(String message) {
        infoText.setManaged(false);
        infoText.setVisible(false);
        errorText.setManaged(true);
        errorText.setVisible(true);
        errorText.setText(message);
    }

    public void hideErrorText() {
        errorText.setVisible(false);
    }

}