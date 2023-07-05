package boardTest.member;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.JSONObject;

import boardTest.DBConn.DBConnect;
import boardTest.extend.BoardSuper;

public class MemberDAO extends BoardSuper{
	
	public MemberDAO(){
		super();
	}
	
	public MemberDAO(DataOutputStream dos, DataInputStream dis) {
		super(dos, dis);
	}
	
	public void login(JSONObject request) {
		int statusOK = 1;
		response(statusOK, request);
	}
	
	public void logout(JSONObject request) {
		int statusOK = 1;
		response(statusOK, request);
	}
	
	public void insert(JSONObject request) {
		try {
			JSONObject data = request.getJSONObject("data");
			String id = data.getString("id").trim();
			String name = data.getString("name").trim();
			String pwd = data.getString("pwd").trim();
			String email = data.getString("email").trim();

			sql = new StringBuilder().append("INSERT INTO Member").append("(id, name, pwd, email) ")
					.append("values(?, ?, ?, ?)").toString();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, pwd);
			pstmt.setString(4, email);

			row = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBConnect.discon();
			response(row, request);
		}
	}
	
	// Member문 update 
	public void update(JSONObject request) {
		try {
			JSONObject data = request.getJSONObject("data");
			
			int nid = data.getInt("nid");
			String pwd = data.getString("pwd").trim();
			String changeOption = data.getString("changeOption");
			
			switch (changeOption) {
			case "1" -> {
				sql = new StringBuilder()
						.append("UPDATE Member ")
						.append("SET ")
						.append("pwd = ? ")
						.append("where nid = ?").toString();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, pwd);
				pstmt.setInt(2, nid);
				}
			case "2" -> {
				String email = data.getString("email").trim();
				String name = data.getString("name").trim();
				
				sql = new StringBuilder().append("UPDATE Member ")
						.append("SET ")
						.append("name = ?, ")
						.append("pwd = ?, ")
						.append("email = ? ")
						.append("where nid = ?")
						.toString();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, pwd);
				pstmt.setString(3, email);
				pstmt.setInt(4, nid);
				}
			}

			row = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBConnect.discon();
			response(row,request);
		}
	}
	
	// member delete
	@Override
	public void delete(JSONObject request) {
		JSONObject data = request.getJSONObject("data");
		table_name = data.getString("tablename").trim();
		int delNid = data.getInt("nid");
		sql = new StringBuilder()
				.append("DELETE FROM ")
				.append(table_name)
				.append(" where nid = ?").toString();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, delNid);
			row = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnect.discon();
		}
		response(row, request);
	}
	
}