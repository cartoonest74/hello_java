package hello_java.dbms;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class FunctionCallExam {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/orcl",
					"java",
					"oracle");
			String sql = "{? = call user_login(?,?)}";
			CallableStatement cstmt = conn.prepareCall(sql);
			
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setString(2,"SPRING");
			cstmt.setString(3,"4444");
			
			cstmt.execute();
			int result = cstmt.getInt(1);
			
			cstmt.close();
			
			String message = switch(result) {
				case 0 -> "login success";
				case 1 -> "password x";
				default -> "not found id";
			};
			System.out.println(message);
			
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
