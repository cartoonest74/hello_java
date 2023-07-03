package boardTest.member;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
			String id = data.getString("id");
			String name = data.getString("name");
			String pwd = data.getString("pwd");
			String email = data.getString("email");

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
			String pwd = data.getString("pwd");
			String email = data.getString("email");
			int changeOption = data.getInt("changeOption");
			switch (changeOption) {
			case 1 -> {
				sql = new StringBuilder()
						.append("UPDATE Member ")
						.append("SET ")
						.append("pwd = ? ")
						.append("where nid = ?").toString();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, pwd);
				pstmt.setInt(2, nid);
			}
			case 2 -> {
				sql = new StringBuilder().append("UPDATE Member ")
						.append("SET ")
						.append("pwd = ?, ")
						.append("email = ? ")
						.append("where nid = ?")
						.toString();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, pwd);
				pstmt.setString(2, email);
				pstmt.setInt(3, nid);
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
	
}