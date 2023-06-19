package hello_java.dbms;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardExam {
	Connection conn = null;
	
	private void connection() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/orcl",
					"java",
					"oracle");
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	private void insert() {
		try {
			connection();
			
			String sql = ""+
			"INSERT INTO boards (bno, btitle, bcontent, bwrite, bdate, bfilename, bfiledate)"+
					"VALUES(SEQ_BNO.NEXTVAL, ?, ?, ?, SYSDATE, ?, ?)";
		
			PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"bno"});
			pstmt.setString(1, "BLUE");
			pstmt.setString(2, "봄이 찾아올까요");
			pstmt.setString(3, "GD");
			pstmt.setString(4, "img1.jpg");
			
			File file = new File("C:/Temp/img1.jpg");
			if(file.exists()) {
				pstmt.setBlob(5, new FileInputStream(file));
			}else {
				file.createNewFile();
			}
			
			//sql 문 실행
			int rows = pstmt.executeUpdate();
			System.out.println("저장된 행 수: "+rows);
			
			//bno 값 얻기
			if(rows == 1) {
				ResultSet rs =pstmt.getGeneratedKeys();
				if(rs.next()) {
					int bno = rs.getInt(1);
					System.out.println("저장된 bno: " + bno);
				}
				rs.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			connClose();
		}
	}

	private void connClose() {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BoardExam boardexam = new BoardExam();
		boardexam.insert();
	}

}
