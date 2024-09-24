package Repository;

import Config.DbConnection;
import Models.Material;
import Repository.Interfaces.MaterialRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialRepositoryImpl implements MaterialRepository {

    private Connection connection;

    public MaterialRepositoryImpl() {
        try {
            this.connection = DbConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addMaterial(Material material) {
        String query = "INSERT INTO material (name, vatRate, unitCost, quantity, transportCost, qualityCoefficient, projectId) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, material.getName());
            stmt.setDouble(2, material.getVatRate());
            stmt.setDouble(3, material.getUnitCost());
            stmt.setDouble(4, material.getQuantity());
            stmt.setDouble(5, material.getTransportCost());
            stmt.setDouble(6, material.getQualityCoefficient());
                stmt.setInt(7, material.getProjectId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Material> getAllMaterials() {
        List<Material> materials = new ArrayList<>();
        String query = "SELECT * FROM material";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                materials.add(new Material(
                        rs.getString("name"),
                        rs.getDouble("vatRate"),
                        rs.getInt("projectId"),
                        rs.getDouble("unitCost"),
                        rs.getDouble("quantity"),
                        rs.getDouble("transportCost"),
                        rs.getDouble("qualityCoefficient")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materials;
    }

    public List<Material> getMaterialById(int projectId) {
        List<Material> materials = new ArrayList<>();
        String query = "SELECT * FROM material WHERE projectId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, projectId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Material material = new Material(
                        rs.getString("name"),
                        rs.getDouble("vatRate"),
                        rs.getInt("projectId"),
                        rs.getDouble("quantity"),
                        rs.getDouble("unitCost"),
                        rs.getDouble("transportCost"),
                        rs.getDouble("qualityCoefficient")
                );
                materials.add(material);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materials;
    }


}
