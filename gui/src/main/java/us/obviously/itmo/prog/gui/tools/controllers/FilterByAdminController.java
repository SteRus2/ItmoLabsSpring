package us.obviously.itmo.prog.gui.tools.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import us.obviously.itmo.prog.common.model.Person;
import us.obviously.itmo.prog.gui.Main;
import us.obviously.itmo.prog.gui.i18n.Internalization;
import us.obviously.itmo.prog.gui.i18n.Language;
import us.obviously.itmo.prog.gui.tools.fields.*;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class FilterByAdminController extends BaseController {

    public GridPane grid;
    public Button submitButton;
    public Button cancelButton;
    public Button deleteButton;
    private List<AbstractField<?, ?, ?>> fieldList;
    private AdminNameField adminNameField;
    private AdminBirthdayField birthdayField;
    private AdminEyeColorField eyeColorField;
    private AdminHairColorField hairColorField;
    private AdminNationalityField nationalityField;
    private CheckBox enabledField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(25, 25, 25, 25));

            fieldList = new LinkedList<>();

            int currentIndex = 0;

            /* ADMIN FIELDS */

            adminNameField = new AdminNameField();
            adminNameField.addToGrid(grid, currentIndex++);
            adminNameField.getControl().setOnAction(this::onEnter);
            fieldList.add(adminNameField);

            birthdayField = new AdminBirthdayField();
            birthdayField.addToGrid(grid, currentIndex++);
            fieldList.add(birthdayField);

            eyeColorField = new AdminEyeColorField();
            eyeColorField.addToGrid(grid, currentIndex++);
            fieldList.add(eyeColorField);

            hairColorField = new AdminHairColorField();
            hairColorField.addToGrid(grid, currentIndex++);
            fieldList.add(hairColorField);

            nationalityField = new AdminNationalityField();
            nationalityField.addToGrid(grid, currentIndex++);
            fieldList.add(nationalityField);

            /* ADMIN FIELDS FINISH */

            enabledField = new CheckBox("field.replaceIfGreater");
            enabledField.setSelected(Main.filterByAdmin);
            grid.add(enabledField, 0, currentIndex);

            this.applyValue(Main.adminFilter);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }

    private void applyValue(Person person) {
        adminNameField.setValue(person.getName());
        birthdayField.setValue(person.getBirthday());
        eyeColorField.setValue(person.getEyeColor());
        hairColorField.setValue(person.getHairColor());
        nationalityField.setValue(person.getNationality());
    }

    @FXML
    private void apply(ActionEvent actionEvent) {
        if (this.validate()) {
            var adminName = adminNameField.getValue();
            var birthday = birthdayField.getValue();
            var eyeColor = eyeColorField.getValue();
            var hairColor = hairColorField.getValue();
            var nationality = nationalityField.getValue();

            Main.adminFilter = new Person(adminName, birthday, eyeColor, hairColor, nationality);
            Main.filterByAdmin = enabledField.isSelected();

            this.close(actionEvent);
        }
    }

    @FXML
    private void onEnter(ActionEvent actionEvent) {
        this.apply(actionEvent);
    }

    private boolean validate() {
        var dirtyFields = new LinkedList<AbstractField<?, ?, ?>>();
        this.fieldList.forEach(field -> {
            var errorList = field.validate();
            if (errorList.isEmpty()) return;
            dirtyFields.add(field);
        });
        if (dirtyFields.isEmpty()) {
            return true;
        }
        dirtyFields.getFirst().getControl().requestFocus();
        return false;
    }

    @Override
    public void setBundle(Language language) {
        submitButton.setText(Internalization.getTranslation("tool.filterByAdmin.submit"));
        cancelButton.setText(Internalization.getTranslation("tool.filterByAdmin.cancel"));
        enabledField.setText(Internalization.getTranslation("field.adminFilterEnabled"));
    }
}
