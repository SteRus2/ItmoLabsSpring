package us.obviously.itmo.prog.gui.tools.controllers;

import javafx.event.ActionEvent;
import us.obviously.itmo.prog.common.server.exceptions.BadRequestException;
import us.obviously.itmo.prog.gui.Main;
import us.obviously.itmo.prog.gui.i18n.Language;

import java.net.URL;
import java.util.ResourceBundle;

public class ClearController extends BaseController {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showInfoText("Вы уверены, что хотите очистить коллекцию?");
    }


    public void apply(ActionEvent actionEvent) {
        showInfoText("Загрузка...");
        try {
            Main.manager.getDataCollection().clearData();
            this.close(actionEvent);
        } catch (BadRequestException e) {
            showErrorText(e.getMessage());
        }
    }

    @Override
    public void setBundle(Language language) {

    }
}
