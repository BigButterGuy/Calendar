package design.voight;

import java.time.LocalDate;
import java.util.Scanner;

public class ProjectManager {


    public void addProject() {
        //TODO use UI to make date setting more seamless.
        System.out.println("Name: ");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        System.out.println("Description: ");
        String description = in.nextLine();
        System.out.println("Start Date (year-mm-dd): ");
        LocalDate startDate = LocalDate.parse(in.nextLine());
        System.out.println("End Date (year-mm-dd): ");
        LocalDate endDate = LocalDate.parse(in.nextLine());
        Project a = new Project(name, startDate, endDate, description);
        printProj(a);
    }
    public void printProj(Project project) { //remove//TODO use maven test on this
        System.out.println(project.getName());
        System.out.println(project.getStartDate());
    }
}
