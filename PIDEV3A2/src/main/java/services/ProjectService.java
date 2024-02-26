package services;

import java.sql.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import models.Project;
import java.util.List;
import java.util.ArrayList;
public class ProjectService {
    private Map<String, Project> projects;
    private Connection connection;

    public ProjectService(Connection connection) {
        this.projects = new HashMap<>();
        this.connection = connection;
    }


    public void addProject(String projectName, Project projectDetails) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO projects ("
                        + "image,name, description, target_audience, demand_in_market, "
                        + "development_timeline, budget_funding_requirements, "
                        + "risk_analysis, market_strategy, exit_strategy, "
                        + "team_background, tags, unique_selling_points, "
                        + "daily_price_of_assets, investors_equity"
                        + ") VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
        )) {
            statement.setString(1,projectDetails.getImage());
            statement.setString(2, projectDetails.getNAME());
            statement.setString(3, projectDetails.getDescription());
            statement.setString(4, projectDetails.getTargetAudience());
            statement.setString(5, projectDetails.getDemandInMarket());
            statement.setString(6, projectDetails.getDevelopmentTimeline());
            statement.setDouble(7, projectDetails.getBudgetFundingRequirements());
            statement.setString(8, projectDetails.getRiskAnalysis());
            statement.setString(9, projectDetails.getMarketStrategy());
            statement.setString(10, projectDetails.getExitStrategy());
            statement.setString(11, projectDetails.getTeamBackground().toString());
            statement.setString(12, projectDetails.getTags().toString());
            statement.setString(13, projectDetails.getUniqueSellingPoints());
            statement.setString(14, projectDetails.getDailyPriceOfAssets().toString());
            statement.setString(15, projectDetails.getInvestorsEquity());


            // Execute the query
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Project getProject(String projectName) {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM projects WHERE name = ?"
        )) {
            statement.setString(1, projectName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Project retrievedProject = new Project();
                retrievedProject.setId(Integer.parseInt(resultSet.getString("id")));
                retrievedProject.setImage(resultSet.getString("image"));
                retrievedProject.setNAME(resultSet.getString("name"));
                retrievedProject.setDescription(resultSet.getString("description"));
                retrievedProject.setTargetAudience(resultSet.getString("target_audience"));
                retrievedProject.setDemandInMarket(resultSet.getString("demand_in_market"));
                retrievedProject.setDevelopmentTimeline(resultSet.getString("development_timeline"));
                retrievedProject.setBudgetFundingRequirements(resultSet.getDouble("budget_funding_requirements"));
                retrievedProject.setRiskAnalysis(resultSet.getString("risk_analysis"));
                retrievedProject.setMarketStrategy(resultSet.getString("market_strategy"));
                retrievedProject.setExitStrategy(resultSet.getString("exit_strategy"));
                retrievedProject.setTeamBackground(Collections.singletonList(resultSet.getString("team_background")).toString());
                retrievedProject.setTags(Collections.singletonList(resultSet.getString("tags")));
                retrievedProject.setUniqueSellingPoints(resultSet.getString("unique_selling_points"));
                // Ensure that dailyPriceOfAssets is properly retrieved from the database
                // retrievedProject.setDailyPriceOfAssets(yourMethodToRetrieveMap(resultSet.getString("daily_price_of_assets")));
                retrievedProject.setInvestorsEquity(resultSet.getString("investors_equity"));
                return retrievedProject;
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return null;
        }

        return null;
    }

    public void updateProjectDetails(String projectName, Project updatedDetails) {
        try {
            String sql = "UPDATE projects SET image=?,name=?, description=?, target_audience=?, " +
                    "demand_in_market=?, development_timeline=?, " +
                    "budget_funding_requirements=?, risk_analysis=?, " +
                    "market_strategy=?, exit_strategy=?, " +
                    "team_background=?, tags=?, unique_selling_points=?, " +
                    "daily_price_of_assets=?, investors_equity=? " +
                    "WHERE name=?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1,updatedDetails.getImage());
                preparedStatement.setString(2, updatedDetails.getNAME());
                preparedStatement.setString(3, updatedDetails.getDescription());
                preparedStatement.setString(4, updatedDetails.getTargetAudience());
                preparedStatement.setString(5, updatedDetails.getDemandInMarket());
                preparedStatement.setString(6, updatedDetails.getDevelopmentTimeline());
                preparedStatement.setDouble(7, updatedDetails.getBudgetFundingRequirements());
                preparedStatement.setString(8, updatedDetails.getRiskAnalysis());
                preparedStatement.setString(9, updatedDetails.getMarketStrategy());
                preparedStatement.setString(10, updatedDetails.getExitStrategy());
                preparedStatement.setString(11, updatedDetails.getTeamBackground().toString());
                preparedStatement.setString(12, updatedDetails.getTags().toString());
                preparedStatement.setString(13, updatedDetails.getUniqueSellingPoints());
                preparedStatement.setString(14, updatedDetails.getDailyPriceOfAssets().toString());
                preparedStatement.setString(15, updatedDetails.getInvestorsEquity());
                preparedStatement.setString(16, projectName);


                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProject(String projectName) {
        try {
            String sql = "DELETE FROM projects WHERE name=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, projectName);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected == 0) {
                    System.out.println("No project found with the name: " + projectName);
                } else {
                    System.out.println("Project deleted successfully: " + projectName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your error-handling strategy
        }
    }
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM projects"
        )) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Project project = new Project();
                project.setId(Integer.parseInt(resultSet.getString("id")));
                project.setImage(resultSet.getString("image"));
                project.setNAME(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setTargetAudience(resultSet.getString("target_audience"));
                project.setDemandInMarket(resultSet.getString("demand_in_market"));
                project.setDevelopmentTimeline(resultSet.getString("development_timeline"));
                project.setBudgetFundingRequirements(resultSet.getDouble("budget_funding_requirements"));
                project.setRiskAnalysis(resultSet.getString("risk_analysis"));
                project.setMarketStrategy(resultSet.getString("market_strategy"));
                project.setExitStrategy(resultSet.getString("exit_strategy"));
                project.setTeamBackground(Collections.singletonList(resultSet.getString("team_background")).toString());
                project.setTags(Collections.singletonList(resultSet.getString("tags")));
                project.setUniqueSellingPoints(resultSet.getString("unique_selling_points"));
                // Ensure that dailyPriceOfAssets is properly retrieved from the database
                // project.setDailyPriceOfAssets(yourMethodToRetrieveMap(resultSet.getString("daily_price_of_assets")));
                project.setInvestorsEquity(resultSet.getString("investors_equity"));

                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projects;
    }

}
