package design.voight;


import design.voight.Exceptions.ProjectFileException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class InterfaceController {
    ProjectManager pm;
    @FXML
    private Label newProjectLabel;

    @FXML
    private AnchorPane ganttPane;

    public void initialize() {
        try {
            FXMLLoader ganttLoader = new FXMLLoader(getClass().getResource("/design.voight/ganttChart.fxml"));
            AnchorPane ganttChart = ganttLoader.load();
            ganttPane.getChildren().add(ganttChart); // Add Gantt Chart inside the designated pane
            AnchorPane.setTopAnchor(ganttChart, 0.0);
            AnchorPane.setLeftAnchor(ganttChart, 0.0);
            pm=new ProjectManager();
        } catch (IOException | ProjectFileException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @FXML
    public void onNewProjectButtonClick() throws IOException {
        newProjectLabel.setText("Creating New Project");
        System.out.println("Attempt Create New Project");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/design.voight/projectPopUp.fxml"));
        Parent root = loader.load();
        // Create new stage
        Stage popupStage = new Stage();
        popupStage.setTitle("Create New Project");
        popupStage.setScene(new Scene(root));

        // Get controller and set stage reference
        ProjectPopupController controller = loader.getController();
        controller.setProgramManager(pm);
        controller.setStage(popupStage);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.showAndWait();// Wait for popup to resolve

        //System.out.println("New Project \"" + controller.getName() + "\" created.");
        newProjectLabel.setText(" ");
        //TODO Unload and reload gantt

    }

}
