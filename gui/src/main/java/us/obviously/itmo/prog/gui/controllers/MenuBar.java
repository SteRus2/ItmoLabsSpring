package us.obviously.itmo.prog.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;
import us.obviously.itmo.prog.gui.i18n.Internalization;
import us.obviously.itmo.prog.gui.i18n.Language;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuBar implements Initializable, Translatable {
    public ComboBox<Language> langComboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Language[] languages = {
                Language.RUSSIAN,
                Language.ENGLISH,
                Language.UKRAINIAN,
                Language.ROMANIAN,
                Language.SPANISH,
        };
        langComboBox.getItems().addAll(languages);
        langComboBox.setValue(Internalization.getCurrentLanguage());
        langComboBox.setConverter(new StringConverter<Language>() {
            @Override
            public String toString(Language object) {
                return Internalization.getTranslation(object.getKey());
            }

            @Override
            public Language fromString(String string) {
                return null;
            }
        });
//        langComboBox.setEditable(true);
//        langComboBox.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
//            if (event.getCode() == KeyCode.TAB) {
//                event.consume();
//            }
//        });
        updateCurrentLanguage();
    }

    @Override
    public void setBundle(Language language) {

    }

    public void langOnAction(ActionEvent actionEvent) {
        Internalization.setLanguage(langComboBox.getValue());
    }

    private void updateCurrentLanguage() {
        setBundle(Internalization.getCurrentLanguage());
    }
}
