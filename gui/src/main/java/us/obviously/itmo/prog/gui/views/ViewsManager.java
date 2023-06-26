package us.obviously.itmo.prog.gui.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import us.obviously.itmo.prog.common.model.StudyGroup;
import us.obviously.itmo.prog.gui.Main;
import us.obviously.itmo.prog.gui.controllers.Translatable;
import us.obviously.itmo.prog.gui.i18n.Internalization;
import us.obviously.itmo.prog.gui.tools.controllers.UpdateController;

import java.io.IOException;
import java.util.Objects;

public class ViewsManager {
    private static Translatable currentController;

    public static void showConnectionView(Stage stage) throws IOException {
        showView(stage, "Connection", "views/connection-view.fxml", "styles/fonts.css", "assets/icon.png");
    }

    public static void showSignInView(Stage stage) throws IOException {
        showView(stage, "Sign In", "views/sign-in-view.fxml", "styles/fonts.css", "assets/icon.png");
    }

    public static void showSignUpView(Stage stage) throws IOException {
        showView(stage, "Sign Up", "views/sign-up-view.fxml", "styles/fonts.css", "assets/icon.png");
    }

    public static void showTableView(Stage stage) throws IOException {
//        showSemesterTableView(stage);
//        showStudyGroupTableGroupByNameView(stage);
        showView(stage, "Table", "views/table-view.fxml", "styles/fonts.css", "assets/icon.png");
    }

    public static void showStudyGroupTableView(Stage stage) throws IOException {
        showView(stage, "Study Group Table", "views/study-group-table-view.fxml", "styles/fonts.css", "assets/icon.png");
    }

    public static void showSemesterTableView(Stage stage) throws IOException {
        showView(stage, "Semester Table", "views/semester-table-view.fxml", "styles/fonts.css", "assets/icon.png");
    }

    public static void showStudyGroupTableGroupByNameView(Stage stage) throws IOException {
        showView(stage, "Group by name", "views/study-group-table-group-by-name-view.fxml", "styles/fonts.css", "assets/icon.png");
    }

    public static void showAddToolView(Stage stage) throws IOException {
        showView(stage, "Adding", "views/tools/add-view.fxml", "styles/fonts.css", "assets/icon.png");
    }

    public static void showUpdateToolView(Stage stage, StudyGroup studyGroup) throws IOException {
        UpdateController controller = showView(stage, "Update", "views/tools/update-view.fxml", "styles/fonts.css", "assets/icon.png");
        controller.setStudyGroup(studyGroup);
    }

    public static void showRemoveToolView(Stage stage) throws IOException {
        showView(stage, "Remove Item", "views/tools/remove-view.fxml", "styles/fonts.css", "assets/icon.png");
    }

    public static void showReplaceToolView(Stage stage) throws IOException {
        showView(stage, "Replace", "views/tools/replace-view.fxml", "styles/fonts.css", "assets/icon.png");
    }

    public static void showClearToolView(Stage stage) throws IOException {
        showView(stage, "Clear", "views/tools/clear-view.fxml", "styles/fonts.css", "assets/icon.png");
    }

    public static void showGroupByNameToolView(Stage stage) throws IOException {
        showView(stage, "Group by name", "views/tools/group-by-name-view.fxml", "styles/fonts.css", "assets/icon.png");
    }

    public static void showInfoToolView(Stage stage) throws IOException {
        showView(stage, "Service information", "views/tools/info-view.fxml", "styles/fonts.css", "assets/icon.png");
    }

    public static void showFilterByGroupAdminToolView(Stage stage) throws IOException {
        showView(stage, "Filter by group admin", "views/tools/filter-by-admin-view.fxml", "styles/fonts.css", "assets/icon.png");
    }

    public static void showSemesterValuesToolView(Stage stage) throws IOException {
        showView(stage, "Semester values", "views/tools/semester-values-view.fxml", "styles/fonts.css", "assets/icon.png");
    }

    private static <T extends Translatable> T showView(Stage stage, String title, String path, String stylesPath, String iconPath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(path));

        Scene scene = new Scene(fxmlLoader.load());
        if (stage.isShowing()) stage.close();

        T currentController = fxmlLoader.getController();
        currentController.setBundle(Internalization.getCurrentLanguage());
        Internalization.applyNewController(currentController);

        stage.setTitle(title);
        scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource(stylesPath)).toExternalForm());
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResource(iconPath)).getFile()));
        stage.setOnCloseRequest(event -> System.exit(1));
        stage.setScene(scene);

        stage.show();

        return currentController;
    }

}
