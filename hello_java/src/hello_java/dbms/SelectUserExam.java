package hello_java.dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectUserExam {

	public static void main(String[] args) {
		Connection conn = null;
		User user =new User();
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/orcl",
					"java",
					"oracle");
			
			String	sql = "select * from users";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rows = pstmt.executeQuery();
			while(rows.next()) {
				user.setUserId(rows.getString("userid"));
				user.setUserName(rows.getString("username"));
				user.setUserPassword(rows.getString("userpassword"));
				user.setUserAge(rows.getInt("userage"));
				user.setUserEmail(rows.getString("useremail"));
			}
			
			System.out.println(user);
			pstmt.close();
			
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
