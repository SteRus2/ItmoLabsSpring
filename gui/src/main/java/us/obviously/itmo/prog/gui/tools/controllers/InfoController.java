package us.obviously.itmo.prog.gui.tools.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import us.obviously.itmo.prog.common.server.data.DataInfo;
import us.obviously.itmo.prog.common.server.exceptions.BadRequestException;
import us.obviously.itmo.prog.gui.Main;
import us.obviously.itmo.prog.gui.i18n.Language;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoController extends BaseController {
    @FXML
    public Text typeText;
    @FXML
    public Text dateText;
    @FXML
    public Text countText;
    private DataInfo info;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.executorService.submit(this::getData);
    }

    public void getData() {
        showInfoText("Загрузка...");
        try {
            this.info = Main.manager.getDataCollection().getInfo();
            this.countText.setText(info.getCount().toString());
            this.typeText.setText(info.getType());
            this.dateText.setText(info.getDate().toString());
            hideInfoText();
        } catch (BadRequestException e) {
            showErrorText(e.getMessage());
        }
    }

    @Override
    public void setBundle(Language language) {

    }
}
