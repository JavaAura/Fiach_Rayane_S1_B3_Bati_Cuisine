package Repository;

import Config.DbConnection;
import Models.Quote;
import Repository.Interfaces.QuoteRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuoteRepositoryImpl implements QuoteRepository {
    private Connection connection;

    public QuoteRepositoryImpl() {
        try {
            this.connection = DbConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addQuote(Quote quote) {
        String query = "INSERT INTO quote (estimatedAmount, emissionDate, validityDate, projectId) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, quote.getEstimatedAmount());
            stmt.setDate(2, new java.sql.Date(quote.getEmissionDate().getTime()));
            stmt.setDate(3, new java.sql.Date(quote.getValidityDate().getTime()));
            stmt.setInt(4, quote.getProjectId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        quote.setProjectId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Failed to obtain the quote ID.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateQuoteStatus(int projectId, boolean status) {
        String query = "UPDATE quote SET accepted = ? WHERE projectId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setBoolean(1, status);
            stmt.setInt(2, projectId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
