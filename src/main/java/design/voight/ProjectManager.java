package design.voight;

import design.voight.Exceptions.ProjectFileException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectManager {
    private List<Project> existingProjects = new ArrayList<>();
    private File projectFile;

    public ProjectManager() throws ProjectFileException {
        this("projects.ser");
    }

    public ProjectManager(String filePath) throws ProjectFileException {
        try {
            System.out.println("Program Manager constructor.");
            projectFile = new File(filePath);
            existingProjects = load();
        } catch (ProjectFileException e) {
            System.err.println("* * * * * * ");
            throw new ProjectFileException(e.getMessage());
        }
    }

    public int save() throws ProjectFileException {// Need catch or no work
        // Open new file stream, then write to it.
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(projectFile));
            out.writeObject(existingProjects);
        } catch (IOException e) {
            throw new ProjectFileException(e.getMessage());
        }

        GanttChartController.onProjectListChanged();
        System.out.println("Wrote " + existingProjects.size() + " items.");
        return existingProjects.size();
    }

    @SuppressWarnings("unchecked")
    private List<Project> load() throws ProjectFileException {

        List<Project> returnList = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(projectFile));
            returnList = (List<Project>) in.readObject();
        } catch (IOException e) {
          // No files was found. Create a fresh arraylist.
            returnList = new ArrayList<>();
        } catch (ClassNotFoundException e) {
            throw new ProjectFileException(e.getMessage());
        }
        System.out.println("Read " + returnList.size() + " items from file.");
        existingProjects = returnList;
        return returnList;
    }

    public List<Project> get() {
        try {
            load();
        } catch (ProjectFileException e) {
            // This should be unreachable code unless somebody deletes the project file while we're using it.
            throw new RuntimeException(e);
        }
        return existingProjects;
    }

    public void printProjects() {
        System.out.println(existingProjects);//TODO print out names of each project iteratively

        for (Project project : existingProjects) {
            System.out.println(project.getName());
            System.out.println(project.getDescription());
            System.out.println(project.getStartDate());
        }
    }

    public void addProject(Project p) {
        existingProjects.add(p);
    }

    public void removeProject(Project p) {
        existingProjects.remove(p);
    }

    //TODO delete projects
    /*public static void deleteProjects(List<Project> existingProjects) {
        for (Project project : existingProjects) {}
    }*/

    //TODO return individual project. iterate through, looking for matching .name, return pointer to project specific
}
