package design.voight;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectManager {
    private static final String FILE_PATH = "projects.ser";


    public static void save(List<Project> projects) {// Need catch or no work
        List<Project> existingProjects = loadProjects();
        existingProjects.addAll(projects);

        // Open new file stream, then write to it.
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(projects);
            System.out.println("Saved " + projects.size() + " projects"); // Remove later
        } catch (IOException e) {
            System.exit(0);
        }
        printProjects(existingProjects);
    }
    @SuppressWarnings("unchecked")
    public static List<Project> loadProjects() {
        File file = new File(FILE_PATH);
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (List<Project>) in.readObject(); // will always be a list
        } catch (IOException | ClassNotFoundException e) {
            System.exit(1);
        }
        return new ArrayList<Project>();

    }
    public static void printProjects(List<Project> existingProjects) {
        System.out.println(existingProjects);
    }
}
