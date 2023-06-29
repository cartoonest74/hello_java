package Select.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import Select.jdbc.DBconn;
import Select.server.SelectServer;
import Select.server.SocketClient;
import Select.vo.Employee;
import Select.vo.Member;
import Select.vo.Professor;
import Select.vo.Student;

public class MemberDAO implements Dao{
	
	SocketClient socketClient = new SocketClient();
	private ResultSet resultSet = null;
	private String sql = "";
	private PreparedStatement pstmt;
	private Connection conn = DBconn.getConnection();
	private DataOutputStream dos;
	private DataInputStream dis;
	
	public MemberDAO() {};
	public MemberDAO(DataOutputStream dos, DataInputStream dis) {
			this.dos = dos;
			this.dis = dis;
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
		String charateristic = data.getString("charateristic");

		
		switch (type) {
			case 1 -> member = new Student(id, name, tel, addr, type, etc, charateristic);
			case 2 -> member = new Professor(id, name, tel, addr, type, etc, charateristic);
			case 3 -> member = new Employee(id, name, tel, addr, type, etc, charateristic);
		}
		
		if(! (member.getClass().getName().equals("Member"))) sql_insert(id, name, tel, addr, type, etc, charateristic);
		
	}

	// TODO SQL_INSERT
	public void sql_insert(String id, String name, String tel, String addr, int type,
			String etc, String character) {
		try {
			JSONObject response = new JSONObject();
			String sql = new StringBuilder()
					.append("INSERT INTO School(id, name, tel, addr, type, etc, charateristic)")
					.append(" values(?, ?, ?, ?, ?, ?, ?)")
					.toString();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, tel);
			pstmt.setString(4, addr);
			pstmt.setInt(5, type);
			pstmt.setString(6, etc);
			pstmt.setString(7, character);
			
			System.out.println("올라zzzz간다!!!!!");
			
			int row = pstmt.executeUpdate();
			System.out.println("올라간다!!!!!");

			if (row < 0) {
				response.put("status", "no");
				dos.writeUTF(response.toString());
				dos.flush();
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconn.discon();
//			DBconn.closeSSH();
		}
	}

	@Override
	public void selectAll() {
		try {
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
				members.put("charateristic", resultSet.getString("charateristic"));
				data.put(members);
			}
			resultSet.close();
			DBconn.discon();
//			DBconn.closeSSH();
			
			JSONObject response = new JSONObject();
			response.put("status", "ok");
			response.put("data", data);
	
			dos.writeUTF(response.toString());
			dos.flush();
			
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
