package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
		private static final String DB_URL = "jdbc:h2:mem:gamescore;DB_CLOSE_DELAY=-1";

		public static Connection getConnection() throws SQLException {
				return DriverManager.getConnection(DB_URL);
		}
}
