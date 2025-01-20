package design.voight;
import design.voight.Exceptions.ProjectFileException;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.List;

public class GanttChartBuilder {

    private static final int DAY_WIDTH = 33;
    private static final int ROW_HEIGHT = 40;
    private static final int[] DAYS_IN_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final String[] monthNames = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
    private GridPane calendarGrid;
    private ProjectManager projectManager;

    /**
     * Pass a GridPane where chart should be made when creating GanttChart
     * @param calendarGrid The GridPane
     */
    public GanttChartBuilder(GridPane calendarGrid, ProjectManager projectManager) {
        this.calendarGrid = calendarGrid;
        this.projectManager = projectManager;
    }

    /**
     * This section will load the existingProjects List
     * Takes in list of projects, iterates through, adds to chart.
     * Project objects will need: String name, LocalDate startDate, LocalDate endDate
     * @param projects is a List of projects with the above variables.
     */
    public void addProjects() {
        List<Project> projects = projectManager.get();
        System.out.println("Adding " + projects.size() + " items to GanttChart.");
        for (Project project : projects) {
            int startDayIndex = convertDateToDayIndex(project.getStartDate());
            int endDayIndex = convertDateToDayIndex(project.getEndDate());
            int width = (endDayIndex - startDayIndex) * DAY_WIDTH;
            Button projectBar = new Button(project.getName());
            projectBar.setPrefWidth((endDayIndex - startDayIndex + 1) * DAY_WIDTH);
            projectBar.setPrefHeight(ROW_HEIGHT - 5);

            GridPane.setColumnIndex(projectBar, startDayIndex);
            GridPane.setRowIndex(projectBar, 5); //TODO dynamically change
            GridPane.setColumnSpan(projectBar, endDayIndex - startDayIndex + 1);
            calendarGrid.getChildren().add(projectBar);
        }
    }

    //TODO Leap year.

    private int convertDateToDayIndex(LocalDate date) {
        return date.getDayOfYear();
    }

    //TODO Add dynamic row creation
    public void generateCalendarGrid() throws ProjectFileException {
        int columnIndex = 0;
        for (int month = 0; month < 12; month++) {
        calendarGrid.add(new Text(monthNames[month]), columnIndex, 0, DAYS_IN_MONTH[month], 1);

            for (int day = 1; day <= DAYS_IN_MONTH[month]; day++) {
                calendarGrid.add(new Text(String.valueOf(day)), columnIndex, 1);
                columnIndex++;
            }
        }
        for (int row = 2; row < 12; row++) {
            for (int col = 0; col < columnIndex; col++) {
                Rectangle placeholder = new Rectangle(DAY_WIDTH, ROW_HEIGHT);
                placeholder.setFill(Color.LIGHTGRAY); // Light gray for testing purposes
                placeholder.setStroke(Color.BLACK);
                calendarGrid.add(placeholder, col, row);
                //calendarGrid.add(new Text(""), col, row); for non-visible rows
            }
        }
        addProjects();// Link project list
    }

    public void refreshChart() {
        Platform.runLater(() -> {
            calendarGrid.getChildren().clear();
            try {
                generateCalendarGrid();
            } catch (ProjectFileException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
