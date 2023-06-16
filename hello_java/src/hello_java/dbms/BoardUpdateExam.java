package hello_java.dbms;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BoardUpdateExam {

	public static void main(String[] args) throws IOException {
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/orcl",
					"java",
					"oracle"); 
			
			String sql = new StringBuilder()
					.append("UPDATE boards SET ")
					.append("btitle= ?, ")
					.append("bcontent= ?, ")
					.append("bfilename= ?, ")
					.append("bfiledate= ? ")
					.append("WHERE bno= ?")
					.toString();
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "gd");
			pstmt.setString(2, "bigbang");
			pstmt.setString(3, "img1.jpg");
			
			File file = new File("C:/Temp/img1.jpg");
			if(file.exists()) {
				pstmt.setBlob(4, new FileInputStream(file));
			}else {
				file.createNewFile();
			}
			pstmt.setInt(5, 3);
			
			int rows = pstmt.executeUpdate();
			System.out.println("저장된 행의 수"+rows);
			
			// PreparedStateMent 닫기
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
