package services;

import models.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Statement;

public class CategoryService {

    private Connection connection;

    public CategoryService(Connection connection) {
        this.connection = connection;
    }

    public void addCategory(Category category) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO categories (category_name, subfield, type_of_funding, profitability_index) " +
                        "VALUES (?, ?, ?, ?)"
        )) {
            statement.setString(1, category.getCategoryName());
            statement.setString(2, category.getSubfield());
            statement.setString(3, category.getTypeOfFunding());
            statement.setString(4, category.getProfitabilityIndex());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Category getCategory(int categoryId) {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM categories WHERE id = ?"
        )) {
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToCategory(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateCategoryDetails(int categoryId, Category updatedDetails) {
        try {
            String sql = "UPDATE categories SET category_name=?, subfield=?, type_of_funding=?, profitability_index=? WHERE id=?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, updatedDetails.getCategoryName());
                preparedStatement.setString(2, updatedDetails.getSubfield());
                preparedStatement.setString(3, updatedDetails.getTypeOfFunding());
                preparedStatement.setString(4, updatedDetails.getProfitabilityIndex());
                preparedStatement.setInt(5, categoryId);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCategory(int categoryId) {
        try {
            String sql = "DELETE FROM categories WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, categoryId);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected == 0) {
                    System.out.println("No category found with the ID: " + categoryId);
                } else {
                    System.out.println("Category deleted successfully with ID: " + categoryId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your error-handling strategy
        }
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM categories");
            while (resultSet.next()) {
                categories.add(mapResultSetToCategory(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    private Category mapResultSetToCategory(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String categoryName = resultSet.getString("category_name");
        String subfield = resultSet.getString("subfield");
        String typeOfFunding = resultSet.getString("type_of_funding");
        String profitabilityIndex = resultSet.getString("profitability_index");

        return new Category(id, categoryName, subfield, typeOfFunding, profitabilityIndex);
    }
}
