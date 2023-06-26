package us.obviously.itmo.prog.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import us.obviously.itmo.prog.client.Client;
import us.obviously.itmo.prog.client.MainClient;
import us.obviously.itmo.prog.client.RemoteDataCollection;
import us.obviously.itmo.prog.client.manager.Manager;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.common.server.exceptions.FailedToConnectToServerException;
import us.obviously.itmo.prog.common.server.exceptions.FailedToReadRemoteException;
import us.obviously.itmo.prog.gui.Main;
import us.obviously.itmo.prog.gui.i18n.Internalization;
import us.obviously.itmo.prog.gui.i18n.Language;
import us.obviously.itmo.prog.gui.views.ViewsManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionController implements Initializable, Translatable {
    private final ExecutorService executorService;
    @FXML
    public Text message;
    @FXML
    public Button repeatButton;
    @FXML
    public Button signInButton;
    @FXML
    public Button signUpButton;

    ErrorLabel errorLabel;

    public ConnectionController() {
        executorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
    }

    @FXML
    public void repeat(ActionEvent actionEvent) {
        load();
    }

    public void load() {

        // int port = Integer.parseInt(this.port.getText())
        this.repeatButton.setManaged(false);
        this.repeatButton.setVisible(false);
        this.signUpButton.setManaged(false);
        this.signInButton.setManaged(false);
        this.message.setManaged(true);
        executorService.submit(this::createConnection);
    }

    private void createConnection() {
        showMessage(ErrorLabel.LOADING);
        try {
            Main.client = new Client(MainClient.port);
            ExecutorService listenerExecutorService = Executors.newSingleThreadExecutor();
            listenerExecutorService.submit(() -> {
                try {
                    Main.client.listener();
                } catch (FailedToReadRemoteException e) {
                    throw new RuntimeException(e);
                }
            });
            Main.dataCollection = new RemoteDataCollection(Main.client);
            var scanner = new Scanner(System.in);
            Main.manager = new Manager<StudyGroup>(Main.dataCollection, scanner);
            Main.client.run();
            this.message.setManaged(false);
            this.message.setVisible(false);
            this.signUpButton.setManaged(true);
            this.signInButton.setManaged(true);
        } catch (FailedToConnectToServerException e) {
            message.setText(e.getMessage());
            this.repeatButton.setManaged(true);
            this.repeatButton.setVisible(true);
        }
    }

    private void showMessage(ErrorLabel label) {
        errorLabel = label;
        displayErrorText();
    }

    private void showSignUpStage(Stage stage) {
        try {
            ViewsManager.showSignUpView(stage);
        } catch (IOException e) {
            showMessage(ErrorLabel.LOADING_RESOURCE);
        }
    }

    private void showSignInStage(Stage stage) {
        try {
            ViewsManager.showSignInView(stage);
        } catch (IOException e) {
            showMessage(ErrorLabel.LOADING_RESOURCE);
        }
    }

    @FXML
    public void signIn(ActionEvent actionEvent) {
        var stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        showSignInStage(stage);
    }

    @FXML
    public void signUp(ActionEvent actionEvent) {
        var stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        showSignUpStage(stage);
    }

    @Override
    public void setBundle(Language language) {
        signInButton.setText(Internalization.getTranslation("connection.signIn"));
        signUpButton.setText(Internalization.getTranslation("connection.signUp"));
        repeatButton.setText(Internalization.getTranslation("connection.repeat"));
        repeatButton.setText(Internalization.getTranslation("connection.repeat"));
        displayErrorText();
    }

    private void displayErrorText() {
        if (errorLabel == null) return;
        message.setText(Internalization.getTranslation(errorLabel.key));
    }

    private enum ErrorLabel {
        LOADING("loading"),
        LOADING_RESOURCE("errors.resourceLoadingError");

        private final String key;

        ErrorLabel(String key) {
            this.key = key;
        }
    }
}
