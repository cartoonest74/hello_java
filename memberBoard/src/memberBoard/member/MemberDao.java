package memberBoard.member;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONObject;

import memberBoard.conn.DBConnect;
import memberBoard.exception.CheckFormatException;
import memberBoard.server.MemberBoardServer;

public class MemberDao implements Dao {
	private DataOutputStream dos;
	private DataInputStream dis;
	private Connection conn = DBConnect.getConnection();
	private List<MemberVo> arr_member;
	
	public MemberDao() {
	}

	public MemberDao(DataOutputStream dos, DataInputStream dis, List<MemberVo> memberList) {
		this.dos = dos;
		this.dis = dis;
		this.arr_member = memberList;
	}

	void response(String status, JSONObject jso) {
		JSONObject respone = new JSONObject();

		respone.put("status", status);
		respone.put("data", jso);

		try {
			dos.writeUTF(respone.toString());
			dos.flush();

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	@Override
	public void insert(JSONObject request) {
		// TODO Auto-generated method stub
		MemberVo member = new MemberVo();

		JSONObject data = request.getJSONObject("data");

		String id = data.getString("id");
		String name = data.getString("name");
		String pwd = data.getString("pwd");
		String email = data.getString("email");

		member.setId(id);
		member.setName(name);
		member.setPwd(pwd);
		member.setEmail(email);

		arr_member.add(member);

		sql_insert(id, name, pwd, email);
		response("ok", new JSONObject());
	}

	// TODO SQL_INSERT
	public void sql_insert(String id, String name, String pwd, String email) {
		try {
			JSONObject response = new JSONObject();
			String sql = new StringBuilder().append("INSERT INTO Member(id, name, pwd, email)")
					.append(" values(?, ?, ?, ?)").toString();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, pwd);
			pstmt.setString(4, email);

			int row = pstmt.executeUpdate();

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
			DBConnect.discon();
//				DBconn.closeSSH();
		}
	}

	@Override
	public void login(JSONObject request){
		// TODO Auto-generated method stub
		JSONObject data = request.getJSONObject("data");
		String id = data.getString("login_id");
		String pwd = data.getString("login_pwd");
		System.out.println("fsdfsfsf:"+id+" "+ pwd);
		String str_status;
		try {
			inspection(id, pwd);
			str_status = "ok";
		} catch (CheckFormatException e) {
			str_status = "no";
		}

		response(str_status, new JSONObject());
	}

	private void inspection(String id, String pwd) throws CheckFormatException {
		for(MemberVo member:arr_member) {	
//			if(member.getId().equals(id)) {
//			}
//			if(member.getPwd().equals(pwd)) {
//			}
//			throw new CheckFormatException("uncorrect pwd");
		}
	}

	@Override
	public void info(JSONObject request) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(JSONObject request) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(JSONObject request) {
		// TODO Auto-generated method stub
	}

	@Override
	public void logout(JSONObject request) {
		// TODO Auto-generated method stub

	}

	@Override
	public void backmenu() {
		// TODO Auto-generated method stub

	}

}
