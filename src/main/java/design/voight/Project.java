package design.voight;

import java.time.LocalDate;

public class Project {
    // Contains info for individual tracked projects
    private String name = "No Name";
    private LocalDate startDate; // needs changing for custom start dates
    private LocalDate endDate;
    private String description = "No Description.";
    private double progress = 0.0;


    public Project(String name, LocalDate startDate, LocalDate endDate, String description) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }
    // Set/Get
    public String getName() {
        return name;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public String getDescription() {
        return description;
    }
    public double getProgress() {
        return progress;
    }
    public void setProgress(double newProgress) {
        progress = newProgress;
    }
}
