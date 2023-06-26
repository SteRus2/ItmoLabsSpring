package us.obviously.itmo.prog.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import us.obviously.itmo.prog.client.Client;
import us.obviously.itmo.prog.client.RemoteDataCollection;
import us.obviously.itmo.prog.client.manager.Management;
import us.obviously.itmo.prog.common.model.Person;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.common.server.exceptions.FailedToReadRemoteException;
import us.obviously.itmo.prog.gui.views.ViewsManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main extends Application {

    public static Client client;
    public static RemoteDataCollection dataCollection;
    public static Stage stage;
    public static Management manager;

    public static Map<Integer, StudyGroup> currentStudyGroups = new HashMap<>();
    public static boolean filterByAdmin = false;
    public static Person adminFilter = new Person();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Main.stage = stage;
        ViewsManager.showConnectionView(stage);
    }
}