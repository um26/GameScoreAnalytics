
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static final String DB_URL = "jdbc:h2:mem:gamescore;DB_CLOSE_DELAY=-1";
    private static boolean initialized = false;

    public static Connection getConnection() throws SQLException {
        // Load H2 driver explicitly
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("H2 Driver not found", e);
        }
        
        Connection conn = DriverManager.getConnection(DB_URL);
        
        // Initialize database tables if not done yet
        if (!initialized) {
            initializeDatabase(conn);
            initialized = true;
        }
        
        return conn;
    }
    
    private static void initializeDatabase(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        
        // Create Players table
        stmt.execute("CREATE TABLE IF NOT EXISTS Players(" +
                    "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(255) NOT NULL)");
        
        // Create Games table
        stmt.execute("CREATE TABLE IF NOT EXISTS Games(" +
                    "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                    "player_id INTEGER NOT NULL," +
                    "score INTEGER NOT NULL," +
                    "played_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                    "FOREIGN KEY (player_id) REFERENCES Players(id))");
        
        stmt.close();
    }
}
