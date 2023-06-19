package us.obviously.itmo.prog.gui.views;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import us.obviously.itmo.prog.gui.Main;

import java.io.IOException;
import java.util.Objects;

public class ViewsManager {
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
        showView(stage, "Table", "views/main-view.fxml", "styles/fonts.css", "assets/icon.png");
    }

    public static void showView(Stage stage, String title, String path, String stylesPath, String iconPath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(path));
        Scene scene = new Scene(fxmlLoader.load());
        if (stage.isShowing()) stage.close();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(1);
            }
        });

        scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource(stylesPath)).toExternalForm());

        stage.setTitle(title);

        stage.getIcons().add(new Image(Main.class.getResource(iconPath).getFile()));
        stage.setScene(scene);
        stage.show();
    }
}
