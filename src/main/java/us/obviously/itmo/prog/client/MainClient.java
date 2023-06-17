package us.obviously.itmo.prog.client;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import us.obviously.itmo.prog.client.console.ConsoleColor;
import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.client.exceptions.FailedToCloseConnection;
import us.obviously.itmo.prog.client.exceptions.FailedToConnectToServerException;
import us.obviously.itmo.prog.client.manager.Management;
import us.obviously.itmo.prog.client.manager.Manager;
import us.obviously.itmo.prog.common.data.DataCollection;
import us.obviously.itmo.prog.common.model.StudyGroup;

import java.util.Scanner;

public class MainClient extends Application {
    public static Client client;
    public static final int port = 11253;

    public static void main(String[] args) {

        launch(args);

        try {
            ConsoleColor.initColors();
            Scanner scanner = new Scanner(System.in);
            client = new Client(port);
            DataCollection dataCollection = new RemoteDataCollection(client);
            Management manager = new Manager<StudyGroup>(dataCollection, scanner);
            client.run();
            manager.run();
            client.stop();
        } catch (FailedToCloseConnection e) {
            Messages.printStatement("~reПодключение не закрыто, ВНИМАНИЕ, подключение не закрыто: " + e.getMessage() + "~=");
        } catch (FailedToConnectToServerException e) {
            Messages.printStatement("~reПодключиться не удалось: " + e.getMessage() + "~=");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hello");
        FlowPane root = new FlowPane();
        Label label = new Label();
        Button button = new Button("OK");
        root.getChildren().add(label);
        root.getChildren().add(button);
        button.setOnAction((ae) -> label.setText("Привет!"));
        primaryStage.setScene(new Scene(root,240,120));
        primaryStage.show();
    }
}
