package us.obviously.itmo.prog.gui.tools.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import us.obviously.itmo.prog.common.server.exceptions.BadRequestException;
import us.obviously.itmo.prog.common.server.exceptions.NoSuchIdException;
import us.obviously.itmo.prog.gui.Main;
import us.obviously.itmo.prog.gui.i18n.Language;

import java.net.URL;
import java.util.ResourceBundle;

public class RemoveController extends BaseController {
    public TextField valueField;
    public ComboBox<String> comboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: make translations
        comboBox.getItems().addAll("Удалить по id", "Удалить все элементы строго меньше id", "Удалить все элементы, не меньше id");
    }


    public void apply(ActionEvent actionEvent) {
        // TODO: fix it
        var index = comboBox.getSelectionModel().getSelectedIndex();
        String keyString = valueField.getText();
        if (!keyString.matches("\\d*")) {
            showErrorText("id обязан быть целым положительным числом");
        }
        var key = Integer.parseInt(keyString);
        showInfoText("Зао...");
        try {
            if (index == 0) {
                // Удалить по id
                Main.manager.getDataCollection().removeItem(key);
            } else if (index == 1) {
                // Удалить все элементы, не превышающие id
                Main.manager.getDataCollection().removeLowerKey(key);
            } else if (index == 2) {
                // Удалить все элементы, не меньше id
                Main.manager.getDataCollection().removeGreaterKey(key);
            } else {
                showErrorText("Невозможное действие");
                return;
            }
            this.close(actionEvent);
        } catch (NoSuchIdException e) {
            showErrorText("Элемент не найден");
        } catch (BadRequestException e) {
            showErrorText(e.getMessage());
        }
    }

    public void onEnter(ActionEvent actionEvent) {
        apply(actionEvent);
    }

    @Override
    public void setBundle(Language language) {

    }
}
