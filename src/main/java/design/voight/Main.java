package design.voight;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/interface.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("hello World");
        stage.setScene(scene);
        stage.show();
        /*ProjectManager manager = new ProjectManager();
        manager.addProject();*/
    }
    public static void main(String[] args) {launch();}
}
