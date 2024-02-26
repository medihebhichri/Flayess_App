package utils.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.Project;
import services.ProjectService;
import utils.MyDatabase;

import java.io.IOException;
import java.util.List;

public class AllProjectsController {

    @FXML
    private TableView<Project> projectsTable;

    @FXML
    private TableColumn<Project, String> nameColumn;

    @FXML
    private TableColumn<Project, String> descriptionColumn;

    @FXML
    private TableColumn<Project, String> imageColumn;

    @FXML
    private TableColumn<Project, String> targetAudienceColumn;

    @FXML
    private TableColumn<Project, String> demandInMarketColumn;

    @FXML
    private TableColumn<Project, String> developmentTimelineColumn;

    @FXML
    private TableColumn<Project, String> budgetColumn;

    @FXML
    private TableColumn<Project, String> riskAnalysisColumn;

    @FXML
    private TableColumn<Project, String> marketStrategyColumn;

    @FXML
    private TableColumn<Project, String> exitStrategyColumn;

    @FXML
    private TableColumn<Project, String> teamBackgroundColumn;

    @FXML
    private TableColumn<Project, String> tagsColumn;

    @FXML
    private TableColumn<Project, String> uniqueSellingPointsColumn;

    @FXML
    private TableColumn<Project, String> dailyPriceOfAssetsColumn;

    @FXML
    private TableColumn<Project, String> investorsEquityColumn;

    @FXML
    private Button addProjectButton;
    @FXML
    private Button updateProjectButton; // Add the Update button
    @FXML
    private Button deleteProjectButton;


    private ProjectService projectService;

    public void initialize() {
        projectService = new ProjectService(MyDatabase.getInstance().getConnection());
        initializeTableColumns();
        loadProjects();
    }


    private void initializeTableColumns() {
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNAME()));
        descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        imageColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getImage()));
        targetAudienceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTargetAudience()));
        demandInMarketColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDemandInMarket()));
        developmentTimelineColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDevelopmentTimeline()));
        budgetColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getBudgetFundingRequirements())));
        riskAnalysisColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRiskAnalysis()));
        marketStrategyColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarketStrategy()));
        exitStrategyColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExitStrategy()));
        teamBackgroundColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTeamBackground()));
        tagsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTags().toString()));
        uniqueSellingPointsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUniqueSellingPoints()));
        dailyPriceOfAssetsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDailyPriceOfAssets()));
        investorsEquityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getInvestorsEquity()));
    }

    private void loadProjects() {
        List<Project> allProjects = projectService.getAllProjects();
        ObservableList<Project> projects = FXCollections.observableArrayList(allProjects);
        projectsTable.setItems(projects);
    }

    @FXML
    private void handleAddProject(ActionEvent event) {
        try {
            FXMLLoader categoryAddLoader = new FXMLLoader(getClass().getResource("/category_add.fxml"));
            Parent categoryAddRoot = categoryAddLoader.load();
            Scene categoryAddScene = new Scene(categoryAddRoot);

            // Create a new stage for the "CategoryAdd" interface
            Stage categoryAddStage = new Stage();
            categoryAddStage.setScene(categoryAddScene);
            categoryAddStage.setTitle("Add Category");

            // Show the "CategoryAdd" interface
            categoryAddStage.show();

            // Close the current stage (AllProjectsController)
            Stage currentStage = (Stage) addProjectButton.getScene().getWindow();
            currentStage.close();

            // After adding the project, refresh the table
            loadProjects();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeleteProject(ActionEvent event) {
        Project selectedProject = projectsTable.getSelectionModel().getSelectedItem();

        if (selectedProject == null) {
            // No project selected
            return;
        }

        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Delete Project");
        confirmationDialog.setContentText("Are you sure you want to delete the selected project?");

        // Show confirmation dialog and wait for user response
        confirmationDialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // User clicked OK, delete the project
                projectService.deleteProject(selectedProject.getNAME());
                loadProjects(); // Refresh the table
            }
        });
    }
}
