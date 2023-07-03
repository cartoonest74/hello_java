package boardTest.extend;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import boardTest.DBConn.DBConnect;
import boardTest.member.MemberDTO;

public class BoardSuper<T> implements DAO {
	private DataOutputStream dos;
	private DataInputStream dis;

	// DBConnect
	protected Connection conn = DBConnect.getConnection();

	// sql
	protected PreparedStatement pstmt;
	protected String sql;
	protected int row = 0;
	protected ResultSet rs;

	// switch return value
	protected int commonV = 0;

	// json data key_name
	protected String className = "";
	protected int num_option = 0;
	protected String table_name = "";
	private MemberDTO memberDTO;

	// Member column v
	private String id;
	private String pwd;

	// product column v
	private int delNum;

	public BoardSuper() {
	}

	public BoardSuper(DataOutputStream dos, DataInputStream dis) {
		super();
		this.dos = dos;
		this.dis = dis;
	}

	// response
	protected void response(int row, JSONObject jsonobject) {
		JSONObject response = new JSONObject();

		if (row > 0) {

			response.put("status", "ok");
		}

		if (row == 0) {
			response.put("status", "no");
		}
		response.put("data", jsonobject);
		response.put("row", row);

		try {
			dos.writeUTF(response.toString());
			dos.flush();

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	@Override
	public void insert(JSONObject request) {
	}

	@Override
	public void update(JSONObject request) {
	}

	// TODO DELETE문 통합처리

	// member, product, board문 delete
	@Override
	public void delete(JSONObject request) {
		try {
			JSONObject data = request.getJSONObject("data");
			int deleteALL = data.getInt("deleteALL");
			table_name = data.getString("tablename");
			switch (deleteALL) {
			case 0 -> {
				delNum = data.getInt("num");
				sql = new StringBuilder().append("DELETE FROM ").append(table_name).append(" where num = ?").toString();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, delNum);
			}
			case 1 -> {
				sql = new StringBuilder().append("TRUNCATE ").append(table_name).toString();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			}
			}

			row = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnect.discon();
		}
		response(row, request);
	}

	// TODO SELECT문 통합처리

	// product, member, board문 SELECT
	@Override
	public void select(JSONObject request) {
		JSONObject data = request.getJSONObject("data");
		table_name = data.getString("tablename");
		id = data.getString("id");
		try {
			sql = new StringBuilder("SELECT * FROM ").append(table_name).append(" where id =  ?").toString();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			selectWhile(rs, table_name);
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBConnect.discon();
		}
	}

	// 해당 테이블네임에 따라 필요한 컬럼값을 get해서 response함수를 통해 Data를 output시켜서 service부분에 값을 토스
	private void selectWhile(ResultSet rs, String table_name) throws SQLException {
		JSONArray arr_respone = new JSONArray();
		int textCount = 0;
		while (rs.next()) {
			JSONObject select_data = new JSONObject();
			if (table_name.equals("Member")) {
				select_data.put("nid", rs.getInt("nid"));
				select_data.put("id", rs.getString("id"));
				select_data.put("name", rs.getString("name"));
				select_data.put("pwd", rs.getString("pwd"));
				select_data.put("email", rs.getString("email"));
				arr_respone.put(select_data);
			}

			if (table_name.equals("Product")) {
				select_data.put("num", rs.getInt("num"));
				select_data.put("nid", rs.getInt("nid"));
				select_data.put("id", rs.getString("id"));
				select_data.put("name", rs.getString("name"));
				select_data.put("price", rs.getString("price"));
				select_data.put("content", rs.getString("content"));
				arr_respone.put(select_data);
			}

			if (table_name.equals("Board")) {
				select_data.put("nid", rs.getInt("nid"));
				select_data.put("writer", rs.getString("writer"));
				select_data.put("title", rs.getString("title"));
				select_data.put("content", rs.getString("content"));
				arr_respone.put(select_data);
			}
			++textCount;
		}
		JSONObject toRespone = new JSONObject();
		toRespone.put("classArray", arr_respone);
		response(textCount, toRespone);
	}

	// Only use MemberDAO
	@Override
	public void login(JSONObject request) {
		// TODO Auto-generated method stub

	}

	// Only use MemberDAO
	@Override
	public void logout(JSONObject request) {
		// TODO Auto-generated method stub

	}

}