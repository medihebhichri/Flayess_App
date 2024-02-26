package test;// Main.java

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import utils.controllers.AllProjectsController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent categoryAddRoot = FXMLLoader.load(getClass().getResource("/AllProjects.fxml"));



        Scene categoryAddScene = new Scene(categoryAddRoot, 600, 500);
        categoryAddScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());


        primaryStage.setTitle("Project and Category Management");


        primaryStage.setScene(categoryAddScene);


        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
