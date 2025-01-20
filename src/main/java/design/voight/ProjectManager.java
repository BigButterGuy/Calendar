package design.voight;

import design.voight.Exceptions.ProjectFileException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectManager {
    private static final String FILE_PATH = "projects.ser";


    public static void save(List<Project> projects) throws ProjectFileException {// Need catch or no work
        List<Project> existingProjects = loadProjects();
        existingProjects.addAll(projects);

        // Open new file stream, then write to it.
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(existingProjects);
        } catch (IOException e){
            throw new ProjectFileException(e.getMessage());
        }
        GanttChartController.onProjectListChanged();
    }

    @SuppressWarnings("unchecked")
    public static List<Project> loadProjects() throws ProjectFileException {
        File file = new File(FILE_PATH);
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (List<Project>) in.readObject(); // will always be a list
        } catch (IOException | ClassNotFoundException e) {
            throw new ProjectFileException(e.getMessage());
        }
    }

    public static void printProjects(List<Project> existingProjects) {
        System.out.println(existingProjects);//TODO print out names of each project iteratively

        for (Project project : existingProjects) {
            System.out.println(project.getName());
            System.out.println(project.getDescription());
            System.out.println(project.getStartDate());
        }
    }
    //TODO delete projects
    /*public static void deleteProjects(List<Project> existingProjects) {
        for (Project project : existingProjects) {}
    }*/

    //TODO return individual project. iterate through, looking for matching .name, return pointer to project specific
}
