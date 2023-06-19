package hello_java.dbms;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class ProcedureCallExam {

	public static void main(String[] args) {
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/orcl",
					"java",
					"oracle");
			
			String sql = "{call user_create(?, ?, ?, ?, ?, ?)}";
			CallableStatement cstmt = conn.prepareCall(sql);
			
			cstmt.setString(1, "SPRING");
			cstmt.setString(2,"TOP");
			cstmt.setString(3, "4444");
			cstmt.setInt(4, 35);
			cstmt.setString(5, "bigbang@toto.com");
			cstmt.registerOutParameter(6, Types.INTEGER);
			
			
			cstmt.execute();
			
			int rows = cstmt.getInt(6);
			System.out.println("saved row_num: " + rows);
			
			cstmt.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
			}
		}
	}

}
