package design.voight;

import design.voight.Exceptions.ProjectException;
import design.voight.Exceptions.ProjectFileException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public String getName() {
        return name;
    }

    @FXML
    private void onConfirm() {
        name = nameField.getText();
        LocalDate startDate = startDateField.getValue();
        LocalDate endDate = endDateField.getValue();
        String description = descriptionField.getText();

        // Create the Project object
        try {
            Project theProject = new Project(name, startDate, endDate, description);
            // Close the stage (popup)
            stage.close();

            // Create a mutable list and add the project
            List<Project> projectsToSave = new ArrayList<>();
            projectsToSave.add(theProject);

            // Save the project in a background thread
            new Thread(() -> {
                // Pass the mutable list to save
                try {
                    ProjectManager.save(projectsToSave);
                } catch (ProjectFileException e) {
                    // TODO: Replace this with UI dialog.
                    System.err.println("The loaded file did not contain Projects or could not be found.");
                }
            }).start();
        } catch (ProjectException e) {
            // TODO: Replace this with UI dialog.
            System.err.println("There was a problem with your project: " + e.getMessage());
        }
    }

    @FXML
    private void onCancel() {
        stage.close();
    }
}

