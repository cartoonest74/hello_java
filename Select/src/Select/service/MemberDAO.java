package Select.service;

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
import java.sql.SQLException;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import Select.exception.NullpointerExcep;
import Select.server.SelectServer;
import Select.server.SocketClient;
import Select.vo.Employee;
import Select.vo.Member;
import Select.vo.Professor;
import Select.vo.Student;

public class MemberDAO implements Dao {
	SelectServer selectserver = new SelectServer();
	SocketClient socketClient = new SocketClient();
	private Connection conn = null;
	private Session session = null;
	private ResultSet resultSet = null;
	private String str_mp = "";
	private String sql = "";
	private PreparedStatement pstmt;

	// TODO sequere
	@Override
	public String trans_sequere() throws IOException {
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

	// TODO CONNECTION
	@Override
	public void conn() {

		try {
			JSch jsch = new JSch();
			String localhost = "127.0.0.1";
			str_mp = trans_sequere();
			session = jsch.getSession("root", "101.101.166.204", 50000);
			session.setPassword(str_mp);
			System.out.println("ssh wait--");

			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			int forward_port = session.setPortForwardingL(0, localhost, 3306);

			// jdbc enroll
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://" + localhost + ":" + forward_port + "/school",
					"root",
					"@gksrudwnGOD74");

		} catch (NullPointerException e) {
			new NullpointerExcep("null!");

		} catch (JSchException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	// TODO DISCONNECTION
	@Override
	public void discon() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// TODO session disconnect
	@Override
	public void closeSSH() {
		if (session != null) {
			session.disconnect();
		}
	}

	// TODO INSERT
	@Override
	public void insert(JSONObject request) {
		System.out.println("dao INSERT!!!!");
		JSONObject data = request.getJSONObject("data");
		Member member = new Member();
		String id = data.getString("id");
		String name = data.getString("name");
		String tel = data.getString("tel");
		String addr = data.getString("addr");
		int type = data.getInt("type");
		String etc = data.getString("etc");
		String character = data.getString("character");

		switch (type) {
		case 1 -> {
			member = new Student(id, name, tel, addr, type, etc, character);
			selectserver.arr_member.add(member);
			sql_insert(member, id, name, tel, addr, type, etc, character);
		}
		case 2 -> {
			member = new Professor(id, name, tel, addr, type, etc, character);
			selectserver.arr_member.add(member);
			sql_insert(member, id, name, tel, addr, type, etc, character);
		}
		case 3 -> {
			member = new Employee(id, name, tel, addr, type, etc, character);
			selectserver.arr_member.add(member);
			sql_insert(member, id, name, tel, addr, type, etc, character);
		}
		}
		try {
			JSONObject response = new JSONObject();
			conn();
			String sql = new StringBuilder().append("INSERT INTO ")
					.append("table (id, name, tel, addr, type, etc, characater)").append(" VALUES(?, ?, ?, ?, ?, ?, ?)")
					.toString();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, tel);
			pstmt.setString(4, addr);
			pstmt.setInt(5, type);
			pstmt.setString(6, etc);
			pstmt.setString(7, character);

			int row = pstmt.executeUpdate();

			if (row < 0) {
				response.put("status", "no");
				socketClient.dos.writeUTF(response.toString());
				socketClient.dos.flush();
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			discon();
			closeSSH();
		}
	}

	// TODO SQL_INSERT
	public <T extends Member> void sql_insert(T t1, String id, String name, String tel, String addr, int type,
			String etc, String character) {
		try {
			JSONObject response = new JSONObject();
			conn();
			String sql = new StringBuilder().append("INSERT INTO ").append(t1.getClass().getName())
					.append(" (id, name, tel, addr, type, etc, characater)").append(" VALUES(?, ?, ?, ?, ?, ?, ?)")
					.toString();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, tel);
			pstmt.setString(4, addr);
			pstmt.setInt(5, type);
			pstmt.setString(6, etc);
			pstmt.setString(7, character);

			int row = pstmt.executeUpdate();

			if (row < 0) {
				response.put("status", "no");
				socketClient.dos.writeUTF(response.toString());
				socketClient.dos.flush();
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			discon();
			closeSSH();
		}
	}

	@Override
	public void selectAll(JSONObject request) {
		try {
			conn();
			sql = new StringBuilder()
					.append("SELECT * FROM School")
					.toString();
			
			pstmt = conn.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			
			JSONArray data = new JSONArray();
			while(resultSet.next()){
				JSONObject members = new JSONObject();
				members.put("id", resultSet.getString("id"));
				members.put("name", resultSet.getString("name"));
				members.put("tel", resultSet.getString("tel"));
				members.put("addr", resultSet.getString("addr"));
				members.put("type", resultSet.getInt("type"));
				members.put("etc", resultSet.getString("etc"));
				members.put("character", resultSet.getString("characater"));
				data.put(members);
			}
			resultSet.close();
			discon();
			closeSSH();
			
			JSONObject response = new JSONObject();
			response.put("status", "ok");
			response.put("data", data);
	
			socketClient.dos.writeUTF(response.toString());
			socketClient.dos.flush();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void select(JSONObject request) {

	}

	@Override
	public void update(JSONObject request) {

	}

	@Override
	public void delete(JSONObject request) {

	}

}
