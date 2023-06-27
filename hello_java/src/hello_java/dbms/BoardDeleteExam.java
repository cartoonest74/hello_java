package hello_java.dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BoardDeleteExam {
	public static void main(String[] arg) {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/orcl",
					"java",
					"oracle");
			
			String	sql = new StringBuilder()
						.append("DELETE FROM boards ")
						.append("WHERE bno= ?")
						.toString();
			
//			PreparedStatement pstmt = conn.prepareStatement(sql);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("show tables");
//			pstmt.setInt(1,2);
			
//			int rows = pstmt.executeUpdate();
//			System.err.println("삭제된 행 수" + rows);
			
			while(rs.next()) {
				System.out.println(rs.getString(1));
			}
			
			rs.close();
//			pstmt.close();
			
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
