package utils.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.Project;
import services.ProjectService;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class ProjectUpdateController {

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
    private ComboBox<String> visibilityBox;

    @FXML
    private Button updateButton;

    @FXML
    private Button cancelButton;

    private ProjectService projectService;
    private Project selectedProject;

    public void initialize(Connection connection) {
        projectService = new ProjectService(connection);
        visibilityBox.getItems().addAll("Public", "Private", "Internal");
    }

    public void initData(Project project, ProjectService projectService, Connection connection) {
        setProject(project);
        this.projectService = projectService;
        initialize(connection);
    }

    public void setProject(Project project) {
        selectedProject = project;

        nameField.setText(project.getNAME());
        descriptionArea.setText(project.getDescription());
        startDatePicker.setValue(LocalDate.parse(project.getDevelopmentTimeline()));
        endDatePicker.setValue(LocalDate.parse(project.getDevelopmentTimeline()));
        budgetField.setText(String.valueOf(project.getBudgetFundingRequirements()));
        tagsField.setText(String.join(", ", project.getTags()));
        visibilityBox.setValue(project.getTargetAudience());
    }

    @FXML
    private void handleUpdateButton() {
        selectedProject.setNAME(nameField.getText());
        selectedProject.setDescription(descriptionArea.getText());
        selectedProject.setDevelopmentTimeline(startDatePicker.getValue().toString());
        selectedProject.setDevelopmentTimeline(endDatePicker.getValue().toString());
        selectedProject.setBudgetFundingRequirements(Double.parseDouble(budgetField.getText()));
        selectedProject.setTags(List.of(tagsField.getText().split(", ")));
        selectedProject.setTargetAudience(visibilityBox.getValue());

        projectService.updateProjectDetails(selectedProject.getNAME(), selectedProject);

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancelButton() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}

