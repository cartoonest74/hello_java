package hello_java.dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionExam {
	public static void main(String[] arg) {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			// 연결하기 
			conn = DriverManager.getConnection(
					// TCP (JDBC DRIVER) 
					"jdbc:oracle:thin:@localhost:1521/orcl",
					"java",
					"oracle");
			System.out.println("connection success");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			if(conn != null) {
				try {
					conn.close();
					System.out.println("연결 끊기");
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
			}
		}
	}
}
