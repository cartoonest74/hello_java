package hello_java.dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserInsertExam {

	public static void main(String[] args) {
		Connection conn =null;
		
		try {
		
			// jdbc driver enroll
			Class.forName("oracle.jdbc.OracleDriver");
			// connect
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/orcl",
					"java",
					"oracle");
		
			//매개변수화된 SQL문 작성
			String sql = ""+
					"INSERT INTO users (userid, username, userpassword, userage, useremail)" 
					+
					"VALUES(?, ?, ?, ?, ?)";
			
			//PreparedStatement  언기 및 값 지정
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "gdragon");
			pstmt.setString(2, "bigbang");
			pstmt.setString(3, "12345");
			pstmt.setInt(4, 18);
			pstmt.setString(5, "gdragon@bigbang.com");
			
			int rows = pstmt.executeUpdate();
			System.err.println("저장된 행 수: "+rows);
			
			pstmt.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
