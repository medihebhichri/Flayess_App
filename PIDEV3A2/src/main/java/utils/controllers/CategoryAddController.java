package utils.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import models.Category;
import services.CategoryService;
import utils.MyDatabase;

import java.io.IOException;
import java.sql.Connection;



import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import models.Category;
import services.CategoryService;
import utils.MyDatabase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class CategoryAddController {

    @FXML
    private TextField categoryNameField;

    @FXML
    private TextField subfieldField;

    @FXML
    private TextField typeOfFundingField;

    @FXML
    private TextField profitabilityIndexField;

    @FXML
    private Button saveCategoryButton;

    @FXML
    private ComboBox<String> projectCategoryComboBox;

    private Stage projectAddStage;

    public void setProjectAddStage(Stage projectAddStage) {
        this.projectAddStage = projectAddStage;
    }

    @FXML
    private void initialize() {
        // Initialize the project category combo box
        projectCategoryComboBox.setItems(FXCollections.observableArrayList("Education", "Health", "Technology", "Art", "Other"));
    }

    @FXML
    private void saveCategoryButtonClicked() {
        String categoryName = categoryNameField.getText();
        String subfield = subfieldField.getText();
        String typeOfFunding = typeOfFundingField.getText();
        String profitabilityIndex = profitabilityIndexField.getText();

        // Assuming 0 as the default id for a new category
        Category category = new Category(0, categoryName, subfield, typeOfFunding, profitabilityIndex);

        try {
            Connection connection = MyDatabase.getInstance().getConnection();
            CategoryService categoryService = new CategoryService(connection);
            categoryService.addCategory(category);
            System.out.println("Category saved to the database.");

            // Call the method to redirect to the ProjectAddController
            redirectToProjectAdd();

        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    @FXML
    private void addProjectToCategory() {
        // Your logic to associate the selected project with the selected category
        String selectedCategory = projectCategoryComboBox.getValue();
        if (selectedCategory != null) {
            System.out.println("Selected Category: " + selectedCategory);
            redirectToProjectAdd(); // Redirect when the "Add Project" button is clicked
        } else {
            System.out.println("No category selected.");
        }
    }

    private void redirectToProjectAdd() {
        try {
            // Load the "ProjectAdd" interface
            FXMLLoader projectAddLoader = new FXMLLoader(getClass().getResource("/AddProject.fxml"));
            Parent projectAddRoot = projectAddLoader.load();
            Scene projectAddScene = new Scene(projectAddRoot);

            // Create a new stage for the "ProjectAdd" interface
            Stage projectAddStage = new Stage();
            projectAddStage.setScene(projectAddScene);
            projectAddStage.setTitle("Add Project");


            // Set the stage or scene of the "ProjectAdd" interface in the ProjectAddController
            ProjectAddController projectAddController = projectAddLoader.getController();
            projectAddController.setCategoryStage(projectAddStage);

            // Hide the "CategoryAdd" interface
            Stage categoryAddStage = (Stage) saveCategoryButton.getScene().getWindow();
            categoryAddStage.hide();

            // Show the "ProjectAdd" interface
            projectAddStage.showAndWait();

            // After the "ProjectAdd" interface is closed, show the "CategoryAdd" interface

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
