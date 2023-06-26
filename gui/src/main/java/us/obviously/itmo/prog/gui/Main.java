package us.obviously.itmo.prog.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import us.obviously.itmo.prog.client.Client;
import us.obviously.itmo.prog.client.RemoteDataCollection;
import us.obviously.itmo.prog.client.manager.Management;
import us.obviously.itmo.prog.common.model.Person;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.gui.views.ViewsManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main extends Application {

    public static Client client;
    public static RemoteDataCollection dataCollection;
    public static Stage stage;
    public static Management manager;

    public static Map<Integer, StudyGroup> currentStudyGroups = new HashMap<>();
    public static boolean filterByAdmin = false;
    public static Person adminFilter = new Person();
    public static String searchText = "";

    public static void main(String[] args) {
        launch();
    }

    public static void setHashMap(Map<Integer, StudyGroup> hashMap) {
        currentStudyGroups = hashMap;
        updateTextFilter();
    }

    public static void updateTextFilter() {
        currentStudyGroups = currentStudyGroups.entrySet().stream().filter(integerStudyGroupEntry -> {
            var search = Main.searchText.toLowerCase();
            StudyGroup group = integerStudyGroupEntry.getValue();
            if (group.getName().toLowerCase().contains(search)) {
                return true;
            }
            if (group.getOwnerUsername().toLowerCase().contains(search)) {
                return true;
            }
            if (group.getGroupAdmin().getName().toLowerCase().contains(search)) {
                return true;
            }
            if (group.getCreationDate().toString().toLowerCase().contains(search)) {
                return true;
            }
            if (group.getGroupAdmin().getBirthday().toString().toLowerCase().contains(search)) {
                return true;
            }
            if (String.valueOf(group.getOwnerId()).contains(search)) {
                return true;
            }
            if (String.valueOf(group.getId()).contains(search)) {
                return true;
            }

            return false;
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public void start(Stage stage) throws IOException {
        Main.stage = stage;
        ViewsManager.showConnectionView(stage);
    }
}