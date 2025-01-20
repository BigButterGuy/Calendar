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
    private ProjectManager pm;

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
        try {
            name = nameField.getText();
            LocalDate startDate = startDateField.getValue();
            LocalDate endDate = endDateField.getValue();
            String description = descriptionField.getText();

            // Create the Project object
            Project theProject = new Project(name, startDate, endDate, description);
            // Close the stage (popup)
            stage.close();

            // Create a mutable list and add the project
            pm.addProject(theProject);

            // Pass the mutable list to save
            pm.save();

        } catch (ProjectException | ProjectFileException e) {
            // TODO: Replace this with UI dialog.
            System.err.println("There was a problem with your project: " + e.getMessage());
        }
    }

    @FXML
    private void onCancel() {
        stage.close();
    }

    public void setProgramManager(ProjectManager pm) {
        this.pm = pm;
    }
}

