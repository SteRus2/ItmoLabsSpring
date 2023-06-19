/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.obviously.itmo.prog.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import us.obviously.itmo.prog.common.action_models.UserModel;
import us.obviously.itmo.prog.common.server.exceptions.BadRequestException;
import us.obviously.itmo.prog.gui.Main;
import us.obviously.itmo.prog.gui.views.ViewsManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SignUpController implements Initializable {

    private final ExecutorService executorService;
    @FXML
    public Text infoMessage;
    Window window;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button signUpButton;
    @FXML
    private Text errorMessage;

    public SignUpController() {
        executorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void signUp(ActionEvent event) {
        var username = this.usernameField.getText();
        var password = this.passwordField.getText();

        if (this.validate(username, password)) {
            this.executorService.submit(() -> signUpRequest(username, password, event));
        }
    }

    private void signUpRequest(String username, String password, ActionEvent event) {
        hideErrorMessage();
        showInfoMessage("Загрузка...");
        var user = new UserModel(username, password);
        try {
            var answer = Main.manager.getDataCollection().loginUser(user);
            answer.getLogin();
            hideInfoMessage();
            showTableView((Stage) ((Node) event.getSource()).getScene().getWindow());
        } catch (BadRequestException e) {
//            this.showErrorMessage("Invalid username and password.");
            showErrorMessage(e.getMessage());
            hideInfoMessage();
            usernameField.requestFocus();
        }
    }

    private void showTableView(Stage stage) {
        try {
            ViewsManager.showTableView(stage);
        } catch (IOException e) {
            showErrorMessage("Ошибка загрузки страницы");
        }
    }

    private boolean validate(String username, String password) {

        window = signUpButton.getScene().getWindow();
        if (username.equals("")) {
            showErrorMessage("Username text field cannot be blank.");
            usernameField.requestFocus();
//        } else if (username.length() < 5 || username.getText().length() > 25) {
//            showErrorMessage("Username text field cannot be less than 5 and greater than 25 characters.");
//            username.requestFocus();
        } else if (password.equals("")) {
            showErrorMessage("Password text field cannot be blank.");
            passwordField.requestFocus();
//        } else if (password.length() < 5 || password.length() > 25) {
//            showErrorMessage("Password text field cannot be less than 5 and greater than 25 characters.");
//            password.requestFocus();
        } else {
            return true;
        }
        return false;
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

    @FXML
    private void showSignInStage() {
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        try {
            ViewsManager.showSignInView(stage);
        } catch (IOException e) {
            showErrorMessage("Ошибка загрузки страницы");
        }
    }
}