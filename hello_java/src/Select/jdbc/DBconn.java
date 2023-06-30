package Select.jdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.jcraft.jsch.Session;

public class DBconn {
	private static Connection conn = null;
	private static Session session = null;
	private static String str_mp = "";
	
	private DBconn() {}
	
	@SuppressWarnings("resource")
	public static String trans_sequere() throws IOException {
		BufferedReader br;
		File file = new File("C:/Temp/test3.txt");
		InputStream is = new FileInputStream(file);
		if (file.exists()) {
			br = new BufferedReader(new InputStreamReader(is));
			str_mp = br.readLine();
			br.close();
			return str_mp;
		}
		return null;
	}
	
	public static Connection getConnection() {
		if(conn == null) {
			try {
//				JSch jsch = new JSch();
//				String localhost = "127.0.0.1";
//				str_mp = trans_sequere();
//				session = jsch.getSession("root", "101.101.166.204", 50000);
//				session.setPassword(str_mp);
//				System.out.println("ssh wait--");
//				
//				Properties config = new Properties();
//				config.put("StrictHostKeyChecking", "no");
//				session.setConfig(config);
//				session.connect();
//				int forward_port = session.setPortForwardingL(0, localhost, 3306);
//				
				// jdbc enroll
				Class.forName("com.mysql.cj.jdbc.Driver");
//				conn = DriverManager.getConnection(
//						"jdbc:mysql://" + localhost + ":" + forward_port + "/school?autoReconnect=true",
//						"root",
//						"@gksrudwnGOD74");
				
				conn = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1:3306/mydealdb",
						"root",
						"@gksrudwnGOD74");
			} catch (NullPointerException e) {
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		return conn;
	}
	
	// overriding
	public static Connection getConnection(String url, String user, String password) {
		if(conn == null) {
			try {
//				JSch jsch = new JSch();
//				String localhost = "127.0.0.1";
//				session = jsch.getSession(user, url, 50000);
//				session.setPassword(password);
//				System.out.println("ssh wait--");
//				
//				Properties config = new Properties();
//				config.put("StrictHostKeyChecking", "no");
//				session.setConfig(config);
//				session.connect();
//				int forward_port = session.setPortForwardingL(0, localhost, 3306);
//				
				// jdbc enroll
				Class.forName("com.mysql.cj.jdbc.Driver");
//				conn = DriverManager.getConnection(
//						"jdbc:mysql://" + localhost + ":" + forward_port + "/school",
//						"root",
//						"@gksrudwnGOD74");
				conn = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1:3306/mydealdb",
						"root",
						"@gksrudwnGOD74");
			} catch (NullPointerException e) {
				
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		return conn;
	}
	
	public static void discon() {
			try {
				if (conn != null && conn.isClosed()) {
						conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
			conn = null;
	}
	
//	public static void closeSSH() {
//		if (session != null && session.isConnected()) {
//			session.disconnect();
//		}
//		session = null;
//	}
}
