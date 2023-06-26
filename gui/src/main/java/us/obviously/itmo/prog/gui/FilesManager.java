package us.obviously.itmo.prog.gui;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import us.obviously.itmo.prog.common.server.exceptions.ExecuteCommandException;
import us.obviously.itmo.prog.common.server.exceptions.RecurrentExecuteScripts;
import us.obviously.itmo.prog.gui.i18n.Internalization;

import java.io.File;
import java.io.FileNotFoundException;

public class FilesManager {

    public static void executeScript(Stage stage) throws FileNotFoundException, RecurrentExecuteScripts, ExecuteCommandException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(Internalization.getTranslation("files.executeScript"));
        File file = fileChooser.showOpenDialog(stage);
        Main.manager.executeScriptShell(file.getPath());
    }
}
