package design.voight;

import design.voight.Exceptions.ProjectFileException;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class GanttChartController {

    @FXML private GridPane calendarGrid;  // GridPane from FXML
    private static GanttChartBuilder gcBuilder;
    private ProjectManager pm;

    public void initialize() throws ProjectFileException {//TODO call not needed most likely, remove test project, use maven to test.
        pm=new ProjectManager();
        gcBuilder = new GanttChartBuilder(calendarGrid, pm);
        gcBuilder.generateCalendarGrid();
    }

    public static void onProjectListChanged() throws ProjectFileException {
        gcBuilder.refreshChart();
    }
}
