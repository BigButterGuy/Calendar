package design.voight;

import design.voight.Exceptions.ProjectException;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Project implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    // Contains info for individual tracked projects
    private String name = "No Name";
    private LocalDate startDate;
    private LocalDate endDate;
    private String description = "No Description.";
    private double progress = 0.0;

    public Project(String name, LocalDate startDate, LocalDate endDate, String description) throws ProjectException {
        setName(name);
        setStartDate(startDate);
        setEndDate(endDate);
        setDescription(description);
        if(startDate.isAfter(endDate)){
            throw new ProjectException("Start Date must be before End Date.");
        }
    }


    // Set/Get
    public void setName(String name) throws ProjectException {
        if("".equals(name)||null==name){
            throw new ProjectException("Name cannot be empty or null.");
        }
        this.name = name;
    }

    private void setStartDate(LocalDate startDate) throws ProjectException {
        if(null==startDate){
            throw new ProjectException("Start Date cannot be null.");
            // Could be startDate = LocalDate.now()
        }
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) throws ProjectException {
        if(null==startDate){
            throw new ProjectException("End Date cannot be null.");
        }
        this.endDate = endDate;
    }

    public void setDescription(String description) throws ProjectException {
        if("".equals(name)||null==name){ // maybe change
            throw new ProjectException("Description cannot be empty or null.");
        }
        this.description = description;
    }

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
