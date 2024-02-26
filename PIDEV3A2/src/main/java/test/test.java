/*
package test;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hello JavaFX");
        Button button =new Button();
        button.setText("Click me!");
        button.setOnAction(new EventHandler<ActionEvent>(){
            @Override
                    public void handle(ActionEvent event){
                System.out.printf("button is clicked!");

            }
        });
        StackPane root = new StackPane();
        root.getChildren().add(button);
        Scene scene=new Scene(root,250,250);
        primaryStage.setScene(scene);
        primaryStage.show();



    }
}

/*
package test;

import models.Person;
import services.PersonService;
import utils.MyDatabase;

import java.sql.SQLException;
import models.Project;
import services.ProjectService;
import utils.MyDatabase;
import java.util.Collections;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class test {

    public static void main(String[] args) {

        Connection connection = MyDatabase.getInstance().getConnection();


        ProjectService projectService = new ProjectService(connection);


        Project project = createSampleProject();


        projectService.addProject(project.getName(), project);

        Project retrievedProject = projectService.getProject(project.getName());
        System.out.println("Retrieved Project: " + retrievedProject);

        retrievedProject.setDescription("Updated description");
        projectService.updateProjectDetails(project.getName(), retrievedProject);
        System.out.println("Project details updated.");


        retrievedProject = projectService.getProject(project.getName());
        System.out.println("Updated Project: " + retrievedProject);


        //projectService.deleteProject(project.getName());
    }

    private static Project createSampleProject() {

        Map<String, Double> dailyPriceOfAssets = new HashMap<>();
        dailyPriceOfAssets.put("Asset1", 100.0);
        dailyPriceOfAssets.put("Asset2", 200.0);

        return new Project(
                "sample_image.jpg",
                "Sample Project",
                "Sample project description",
                "Target Audience",
                "Demand in Market",
                "Development Timeline",
                100000.0,
                "Risk Analysis",
                "Market Strategy",
                "Exit Strategy",
                // Team background and tags can be initialized as empty lists
                Collections.emptyList(),
                Collections.emptyList(),
                "Unique Selling Points",
                "Investors Equity",
                dailyPriceOfAssets
        );
    }

}







/*
package test;

        import models.Person;
        import services.PersonService;
        import utils.MyDatabase;

        import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        PersonService ps = new PersonService();
        try {
//            ps.create(new Person(23,"insert into ","Ben Foulen"));
//            ps.update(new Person(1,25, "Skander","Ben Foulen"));
            System.out.println(ps.read());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
*/
