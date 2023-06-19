package us.obviously.itmo.prog.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import us.obviously.itmo.prog.client.Client;
import us.obviously.itmo.prog.client.manager.Management;
import us.obviously.itmo.prog.client.manager.Manager;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.common.server.data.DataCollection;
import us.obviously.itmo.prog.gui.views.ViewsManager;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Main extends Application {

    public static Client client;
    public static DataCollection dataCollection;
    public static Stage stage;
    public static Management manager;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Main.stage = stage;
        ViewsManager.showConnectionView(stage);
    }
}