package us.obviously.itmo.prog.gui.tools.controllers;

import javafx.scene.text.Text;
import us.obviously.itmo.prog.common.server.data.DataInfo;
import us.obviously.itmo.prog.common.server.exceptions.BadRequestException;
import us.obviously.itmo.prog.gui.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoController extends BaseController {
    public Text typeText;
    public Text dateText;
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
}
