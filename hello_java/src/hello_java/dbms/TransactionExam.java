package hello_java.dbms;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionExam {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/orcl", 
					"java", 
					"oracle");
			// transaction start
			// autocommit off
			conn.setAutoCommit(false);
			InputStream is =System.in;
			// deposit work
			String deposit_sql = "UPDATE accounts SET balance=balance+? WHERE ano =?";
			PreparedStatement pstmt = conn.prepareStatement(deposit_sql);
			pstmt.setInt(1, 1000);
			pstmt.setString(2, "111-111-1111");
			int rows1 = pstmt.executeUpdate();
			if (rows1 == 0)
				throw new Exception("not deposit");
			pstmt.close();
			System.out.println("false");

			// withdraw work
			String withdraw_sql = "UPDATE accounts SET balance=balance-? WHERE ano=?";
			PreparedStatement pstmt2 = conn.prepareStatement(withdraw_sql);
			pstmt2.setInt(1, 2000);
			pstmt2.setString(2, "111-111-1111");
			int withdraw_rows = pstmt2.executeUpdate();
			if (withdraw_rows == 0)
				throw new Exception("not withdraw");
			pstmt2.close();

			conn.commit();
			System.out.println("transaction success");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
			}
			System.out.println("trasaction fail!!");
		} finally {
			if (conn != null) {
				try {
					conn.setAutoCommit(true);
					conn.close();
				} catch (SQLException e) {
					System.out.println("1");
				}
			}
		}
	}
}
