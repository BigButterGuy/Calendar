package design.voight;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GanttChartController {

    @FXML private GridPane calendarGrid;  // GridPane from FXML
    @FXML private Pane projectPane;      // Pane for project bars

    private static final int DAY_WIDTH = 33;
    private static final int ROW_HEIGHT = 40;
    private static final int[] DAYS_IN_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public void initialize() {
        generateCalendarGrid();
        addProjectBar("Project A", "2025-01-10", "2025-01-20", 0);
    }

    private void generateCalendarGrid() {
        String[] monthNames = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};

        int columnIndex = 0;
        for (int month = 0; month < 12; month++) {
            calendarGrid.add(new Text(monthNames[month]), columnIndex, 0, DAYS_IN_MONTH[month], 1);
            for (int day = 1; day <= DAYS_IN_MONTH[month]; day++) {//check
                calendarGrid.add(new Text(String.valueOf(day)), columnIndex, 1);
                columnIndex++;
            }
        }
    }
    // This section will load the existingProjects List
    private void addProjectBar(String name, String startDate, String endDate, int rowIndex) {
        int startDayIndex = convertDateToDayIndex(startDate);
        int endDayIndex = convertDateToDayIndex(endDate);

        int startX = startDayIndex * DAY_WIDTH;
        int width = (endDayIndex - startDayIndex) * DAY_WIDTH;
        int yPosition = rowIndex * ROW_HEIGHT; // Will need to change to add more rows if overlapping

        Rectangle projectBar = new Rectangle(startX, yPosition, width, ROW_HEIGHT - 5);
        projectBar.setFill(Color.BLUE); //TODO Variable in future

        Text label = new Text(startX + 5, yPosition, name);
        projectPane.getChildren().add(projectBar);
        projectPane.getChildren().add(label);
    }

    private int convertDateToDayIndex(String date) {
        String[] parts = date.split("-");
        int month = Integer.parseInt(parts[1]) - 1;
        int day = Integer.parseInt(parts[2]);
        int dayIndex = 0;
        for (int i = 0; i < month; i++) {
            dayIndex += DAYS_IN_MONTH[i];
        }
        return dayIndex + (day - 1);
    }
}
