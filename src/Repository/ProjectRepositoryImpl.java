package Repository;

import Config.DbConnection;
import Models.Project;
import Repository.Interfaces.ProjectRepository;
import Enum.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryImpl implements ProjectRepository {
    private Connection connection;

    public ProjectRepositoryImpl() {
        try {
            this.connection = DbConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProject(Project project) {
        String query = "INSERT INTO project (projectName, profitMargin, totalCost, projectStatus, customerId) VALUES (?, ?, ?, ?::status, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, project.getProjectName());
            stmt.setDouble(2, project.getProfitMargin());
            stmt.setDouble(3, project.getTotalCost());
            stmt.setString(4, project.getProjectStatus().name());
            stmt.setInt(5, project.getCustomerId());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        project.setId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Failed to obtain the project ID.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProject(Project project) {
        String query = "UPDATE project SET projectName = ?, profitMargin = ?, totalCost = ?, projectStatus = ?::status WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, project.getProjectName());
            stmt.setDouble(2, project.getProfitMargin());
            stmt.setDouble(3, project.getTotalCost());
            stmt.setString(4, project.getProjectStatus().name());
            stmt.setInt(5, project.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        String query = "SELECT * FROM project";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                projects.add(new Project(
                        rs.getInt("id"),
                        rs.getString("projectName"),
                        rs.getDouble("profitMargin"),
                        rs.getDouble("totalCost"),
                        Status.valueOf(rs.getString("projectStatus")),
                        rs.getInt("customerId")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    @Override
    public Project getProjectByName(String projectName) {
        String query = "SELECT * FROM project WHERE projectName = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, projectName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Project(
                        rs.getInt("id"),
                        rs.getString("projectName"),
                        rs.getDouble("profitMargin"),
                        rs.getDouble("totalCost"),
                        Status.valueOf(rs.getString("projectStatus")),
                        rs.getInt("customerId")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
