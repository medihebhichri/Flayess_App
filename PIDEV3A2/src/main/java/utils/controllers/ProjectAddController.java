package utils.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import models.Project;
import models.Category;
import services.ProjectService;
import utils.MyDatabase;

public class ProjectAddController {

    @FXML
    private TextField nameField;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TextField budgetField;

    @FXML
    private TextField tagsField;

    @FXML
    private Button saveButton;

    @FXML
    private ComboBox<Category> categoryComboBox;

    @FXML
    private ComboBox<String> visibilityBox;

    @FXML
    private ImageView projectImage;

    private Stage categoryStage;
    private String path;

    public void setCategoryStage(Stage categoryStage) {
        this.categoryStage = categoryStage;
    }

    @FXML
    private void initialize() {
        visibilityBox.setItems(FXCollections.observableArrayList("Public", "Private", "Custom"));
    }

    @FXML
    private void uploadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            String imagePath = selectedFile.getAbsolutePath();
            System.out.println("Selected Image Path: " + imagePath);

            // Load the image and show it in the ImageView
            try {
                Image image = new Image(new FileInputStream(selectedFile));
                projectImage.setImage(image);
                path = imagePath;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No image file selected.");
        }

        System.out.println("Path after uploadImage: " + path);
    }

    @FXML
    private void saveButtonClicked() {
        String projectName = nameField.getText();
        String description = descriptionArea.getText();
        String startDate = (startDatePicker.getValue() != null) ? startDatePicker.getValue().toString() : "";
        String endDate = (endDatePicker.getValue() != null) ? endDatePicker.getValue().toString() : "";
        String budget = budgetField.getText();
        String tags = tagsField.getText();
        String visibility = visibilityBox.getValue();
        System.out.println("This is the path: " + path);

        // Create a list for tags (assuming tags are comma-separated)
        List<String> tagList = Arrays.asList(tags.split("\\s*,\\s*"));

        Project project = new Project(
                0, // Replace with the actual id
                path,
                projectName,
                description,
                "targetAudience",
                "demandInMarket",
                "developmentTimeline",
                Double.parseDouble(budget),
                "riskAnalysis",
                "marketStrategy",
                "exitStrategy",
                "teamBackground",
                tagList,
                "uniqueSellingPoints",
                "dailyPriceOfAssets",
                "investorsEquity",
                1
        );

        try {
            Connection connection = MyDatabase.getInstance().getConnection();
            ProjectService projectService = new ProjectService(connection);

            projectService.addProject(projectName, project);
            System.out.println("Project saved to the database.");

            // Close only the current stage (ProjectAddController)
            Stage currentStage = (Stage) saveButton.getScene().getWindow();
            currentStage.close();

            // After saving the project, refresh the table in the AllProjectsController
            FXMLLoader allProjectsLoader = new FXMLLoader(getClass().getResource("/AllProjects.fxml"));
            Parent allProjectsRoot = allProjectsLoader.load();
            Scene allProjectsScene = new Scene(allProjectsRoot);

            Stage allProjectsStage = new Stage();
            allProjectsStage.setScene(allProjectsScene);
            allProjectsStage.setTitle("All Projects");

            // Show the "All Projects" interface
            allProjectsStage.show();

        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}

