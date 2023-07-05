package boardTest.DBConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	private static Connection conn = null;
	
		public static Connection getConnection() {
			if(conn == null) {
				try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						conn = DriverManager.getConnection(
								"jdbc:mysql://127.0.0.1:3306/mydealdb",
								"root",
								"@gksrudwnGOD74");
				} catch (SQLException e) {
					e.printStackTrace();
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					
				}
			}
			return conn;
		}
		
		public static void discon() {
				try {
					if(conn != null && conn.isClosed()) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
		}
}