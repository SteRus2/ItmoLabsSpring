/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.obviously.itmo.prog.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import us.obviously.itmo.prog.common.action_models.UserModel;
import us.obviously.itmo.prog.common.server.exceptions.BadRequestException;
import us.obviously.itmo.prog.gui.Main;
import us.obviously.itmo.prog.gui.i18n.Internalization;
import us.obviously.itmo.prog.gui.i18n.Language;
import us.obviously.itmo.prog.gui.views.ViewsManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable, Translatable {

    public Text signInText;
    public Text titleText;
    @FXML
    private Text infoMessage;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button signUpButton;
    @FXML
    private Text errorMessage;
    private ErrorLabels errorLabel;
    private InfoLabels infoLabel;

    public SignUpController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errorMessage.setManaged(false);
    }

    @FXML
    private void signUp(ActionEvent event) {
        var username = this.usernameField.getText();
        var password = this.passwordField.getText();

        if (this.validate(username, password)) {
            signUpRequest(username, password, event);
        }
    }

    private void signUpRequest(String username, String password, ActionEvent event) {
        hideErrorMessage();
        showInfoMessage(InfoLabels.LOADING);
        var user = new UserModel(username, password);
        try {
            var answer = Main.manager.getDataCollection().registerUser(user);
            answer.getLogin();
            hideInfoMessage();
            var stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            showTableView(stage);
        } catch (BadRequestException e) {
            this.showErrorMessage(ErrorLabels.INVALID_CREDENTIALS);
            System.out.println(e.getMessage());
            hideInfoMessage();
            usernameField.requestFocus();
        }
    }

    private void showTableView(Stage stage) {
        try {
            ViewsManager.showTableView(stage);
        } catch (IOException e) {
            showErrorMessage(ErrorLabels.LOADING_RESOURCE);
        }
    }

    private boolean validate(String username, String password) {

        if (username.equals("")) {
            showErrorMessage(ErrorLabels.BLANK_USERNAME);
            usernameField.requestFocus();
//        } else if (username.length() < 5 || username.getText().length() > 25) {
//            showErrorMessage("Username text field cannot be less than 5 and greater than 25 characters.");
//            username.requestFocus();
        } else if (password.equals("")) {
            showErrorMessage(ErrorLabels.BLANK_PASSWORD);
            passwordField.requestFocus();
//        } else if (password.length() < 5 || password.length() > 25) {
//            showErrorMessage("Password text field cannot be less than 5 and greater than 25 characters.");
//            password.requestFocus();
        } else {
            return true;
        }
        return false;
    }

    private void showInfoMessage(InfoLabels message) {
        errorMessage.setManaged(false);
        infoMessage.setManaged(true);
        infoMessage.setVisible(true);
        infoLabel = message;
        displayInfoText();
    }
    private void displayInfoText() {
        if (infoLabel == null) return;
        infoMessage.setText(Internalization.getTranslation(infoLabel.key));
    }

    private void hideInfoMessage() {
        infoMessage.setVisible(false);
    }

    private void showErrorMessage(ErrorLabels message) {
        infoMessage.setManaged(false);
        errorMessage.setManaged(true);
        errorMessage.setVisible(true);
        errorLabel = message;
        displayErrorText();
    }

    @Deprecated
    private void showErrorMessage(String message) {
        infoMessage.setManaged(false);
        errorMessage.setManaged(true);
        errorMessage.setVisible(true);
        errorMessage.setText(message);
    }

    private void displayErrorText() {
        if (errorLabel == null) return;
        errorMessage.setText(Internalization.getTranslation(errorLabel.key));
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
            showErrorMessage(ErrorLabels.LOADING_RESOURCE);
        }
    }

    @FXML
    public void onEnter(ActionEvent actionEvent) {
        this.signUp(actionEvent);
    }

    @Override
    public void setBundle(Language language) {
        titleText.setText(Internalization.getTranslation("signUp.title"));
        signUpButton.setText(Internalization.getTranslation("signUp.submitButton"));
        signInText.setText(Internalization.getTranslation("signUp.haveAccount"));
        usernameField.setPromptText(Internalization.getTranslation("signUp.usernamePrompt"));
        passwordField.setPromptText(Internalization.getTranslation("signUp.passwordPrompt"));
        displayErrorText();
        displayInfoText();
    }

    private enum InfoLabels {
        LOADING("loading");

        private final String key;

        InfoLabels(String key) {
            this.key = key;
        }
    }

    private enum ErrorLabels {
        LOADING_RESOURCE("errors.resourceLoadingError"),
        INVALID_CREDENTIALS("signUp.errors.invalidCredentials"),
        BLANK_USERNAME("signUp.errors.blankUsername"),
        BLANK_PASSWORD("signUp.errors.blankPassword");

        private final String key;

        ErrorLabels(String key) {
            this.key = key;
        }
    }
}