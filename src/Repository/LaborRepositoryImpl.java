package Repository;

import Config.DbConnection;
import Models.Labor;
import Repository.Interfaces.LaborRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LaborRepositoryImpl implements LaborRepository {
    private Connection connection;

    public LaborRepositoryImpl() {
        try{
            this.connection = DbConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addLabor(Labor labor) {
        String query = "INSERT INTO labor (name, vatRate, hourlyRate, workHours, workerProductivity, projectId) VALUES (?, ?, ?, ?, ?, ?)";
        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1, labor.getName());
            stmt.setDouble(2, labor.getVatRate());
            stmt.setDouble(3, labor.getHourlyRate());
            stmt.setDouble(4, labor.getWorkHours());
            stmt.setDouble(5, labor.getWorkerProductivity());
            stmt.setInt(6, labor.getProjectId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Labor> getAllLabors(){
        List<Labor> labors = new ArrayList<>();
        String querry = "SELECT * FROM labor";
        try(Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(querry);
            while(rs.next()){
                labors.add(new Labor(
                        rs.getString("name"),
                        rs.getDouble("vatRate"),
                        rs.getInt("projectId"),
                        rs.getDouble("hourlyRate"),
                        rs.getDouble("workHours"),
                        rs.getDouble("workersProductivity")
                ));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return labors;
    }

    @Override
    public List<Labor> getLaborById(int projectId){
        List<Labor> labors = new ArrayList<>();
        String query = "SELECT * FROM labor where projectId = ?";
        try(PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setInt(1, projectId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Labor labor = new Labor(
                        rs.getString("name"),
                        rs.getDouble("vatRate"),
                        rs.getInt("projectId"),
                        rs.getDouble("hourlyRate"),
                        rs.getDouble("workHours"),
                        rs.getDouble("workersProductivity")
                );
                labors.add(labor);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return labors;
    }

}
