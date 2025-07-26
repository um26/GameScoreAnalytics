package dao;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDAO {

    public static void insertGame(int playerId, int score) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO Games(player_id, score) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, playerId);
            stmt.setInt(2, score);
            stmt.executeUpdate();
            System.out.println("Game added: Player " + playerId + ", Score " + score);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void showTopPlayers() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT Players.name, AVG(score) AS avg_score " +
                         "FROM Games " +
                         "JOIN Players ON Games.player_id = Players.id " +
                         "GROUP BY player_id " +
                         "ORDER BY avg_score DESC " +
                         "LIMIT 5";

            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            System.out.println("Top Players by Average Score:");
            while (rs.next()) {
                String name = rs.getString("name");
                double avg = rs.getDouble("avg_score");
                System.out.printf("%s - %.2f%n", name, avg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
