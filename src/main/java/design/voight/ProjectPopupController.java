package design.voight;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

        // Create the Project object
        Project projectName = new Project(name, startDate, endDate, description);

        // Close the stage (popup)
        stage.close();

        // Create a mutable list and add the project
        List<Project> projectsToSave = new ArrayList<>();
        projectsToSave.add(projectName);

        // Save the project in a background thread
        new Thread(() -> {
            // Pass the mutable list to save
            ProjectManager.save(projectsToSave);
        }).start();
    }
    @FXML
    private void onCancel() {
        stage.close();
    }
}

