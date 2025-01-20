package design.voight;

import design.voight.Exceptions.ProjectFileException;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class GanttChartController {

    @FXML private GridPane calendarGrid;  // GridPane from FXML
    private static GanttChartBuilder gcBuilder;

    public void initialize() throws ProjectFileException {//TODO call not needed most likely, remove test project, use maven to test.
        gcBuilder = new GanttChartBuilder(calendarGrid);
        gcBuilder.generateCalendarGrid();
    }

    public static void onProjectListChanged() throws ProjectFileException {
        gcBuilder.refreshChart();
    }
}
