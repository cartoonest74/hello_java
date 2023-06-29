package hello_java.dbms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class Sqltest {
	private Session session = null;
	Connection conn = null;
	ResultSet rs = null;
	ResultSetMetaData rsmd;
	
	public static void main(String[] args) {
		Sqltest sqltest = new Sqltest();
	}

	public Sqltest() {
		BufferedReader br;

		String str_mp = "";
		String localhost = "127.0.0.1";

		try {

			File file = new File("C:/Temp/test3.txt");
			InputStream is = new FileInputStream(file);
			if (file.exists()) {
				br = new BufferedReader(new InputStreamReader(is));

				str_mp = br.readLine();
				br.close();
			}

			JSch jsch = new JSch();

			session = jsch.getSession("root", "101.101.166.204", 50000);
			session.setPassword(str_mp);
			System.out.println("ssh wait----");

			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			int forward_port = session.setPortForwardingL(0, localhost, 3306);
			System.out.println(forward_port);

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://" + localhost + ":"+forward_port+"/school",
					"root",
					"@gksrudwnGOD74");

			String sql = new StringBuilder()
					.append("INSERT INTO School(id, name, tel, addr, type, etc, charateristic)")
					.append(" values(?, ?, ?, ?, ?, ?, ?)")
					.toString();

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "gdgg");
			pstmt.setString(2, "gdgdgdg");
			pstmt.setString(3, "255415");
			pstmt.setString(4, "dgdgdg");
			pstmt.setInt(5, 1);
			pstmt.setString(6, "gdgdgdg");
			pstmt.setString(7, "gdgdgdgdgd");
//			DatabaseMetaData dmd = conn.getMetaData();
			int row = pstmt.executeUpdate();
//			rsmd = rs.getMetaData();
//			while(rs.next()) {
//                         System.out.println(rs.getString("name"));
//			}
//
//			rs.close();
			if(row != 0) {
				System.out.println("suss");
			}
//			pstmt.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (JSchException e) {
			System.out.println("ssh error");
			e.printStackTrace();

		} finally {
			try {
				if (conn != null) {
					System.out.println("connection ok");
					conn.close();
				}
				if(session != null) {
					System.out.println("session ok");
					closeSSH();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void closeSSH() {
		session.disconnect();
	}
}
