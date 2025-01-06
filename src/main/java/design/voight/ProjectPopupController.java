package design.voight;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class ProjectPopupController {

    private Stage stage;
    private String name;


    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField nameField;

    @FXML
    private DatePicker startDateField;

    @FXML
    private DatePicker endDateField;


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public String getName(){
        return name;
    }

    @FXML
    private void onConfirm() {
        name = nameField.getText();
        LocalDate startDate = startDateField.getValue();
        LocalDate endDate = endDateField.getValue();
        String description = descriptionField.getText();

        Project project = new Project(name, startDate, endDate, description);
        stage.close();  // Close the popup
    }
    @FXML
    private void onCancel() {
        stage.close();
    }
}

