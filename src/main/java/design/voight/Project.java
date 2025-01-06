package design.voight;

import java.time.LocalDate;

public class Project {
    // Contains info for individual tracked projects
    private String name = "No Name";
    private LocalDate startDate;
    private LocalDate endDate;
    private String description = "No Description.";
    private double progress = 0.0;


    public Project(String name, LocalDate startDate, LocalDate endDate, String description) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;

        //temporary check
//        System.out.println(getName());
//        System.out.println(getStartDate());
//        System.out.println(getEndDate());
//        System.out.println(getDescription());
//        System.out.println(getProgress());
        //TODO pass new project to project manager storage
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
