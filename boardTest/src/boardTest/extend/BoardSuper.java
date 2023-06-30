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

import memberBoard.conn.DBConnect;

public class BoardSuper<T> implements DAO {
	private Connection conn = DBConnect.getConnection();
	private int row = 0;
	private DataOutputStream dos;
	private DataInputStream dis;
	private String sql;
	private PreparedStatement pstmt;
	// switch return value
	int commonV = 0;

	// json data key_name
	private String className = "";
	private int num_option = 0;
	private String table_name = "";

	public BoardSuper() {
	}

	// response
	private void response(int commonv, JSONObject jsonobject) {
		JSONObject response = new JSONObject();
		if(commonv > 0) {
			response.put("status", "ok");
		}
		response.put("status", "no");
		response.put("data", jsonobject);
		try {
			dos.writeUTF(response.toString());
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// INSER, UPDATE, SELECT 공통 SWITCH 구문
	private int commonBasicSwitch(JSONObject request) {
		JSONObject data = request.getJSONObject("data");
		int outputCheck = 0;
		num_option = data.getInt("num_option");

		className = data.getString("className");
		if (num_option == 1) {
			switch (className) {
			case "MemberDAO" -> outputCheck = insertMember(data);
			case "ProductDAO" -> outputCheck = insertProduct(data);
			case "BoardDAO" -> outputCheck = insertBoard(data);
			}
			return outputCheck;
		}

		if (num_option == 2) {
			switch (className) {
			case "MemberDAO" -> outputCheck = updateMember(data);
			case "ProductDAO" -> outputCheck = updateProduct(data);
			case "BoardDAO" -> outputCheck = updateBoard(data);
			}
			return outputCheck;
		}

		if (num_option == 3) {
			outputCheck = selectOption(data);
			return outputCheck;
		}
		return outputCheck;
	}

	// TODO insert
	@Override
	public int insert(JSONObject request) {
		commonV = commonBasicSwitch(request);

		response(commonV, new JSONObject());

		return row;
	}

	@Override
	public int update(JSONObject request) {

		commonV=commonBasicSwitch(request);

		response(commonV, new JSONObject());

		return row;
	}

	@Override
	public int select(JSONObject request) {

		commonV=commonBasicSwitch(request);

		response(commonV, new JSONObject());
		return row;
	}

	@Override
	public int delete(JSONObject request) {
		JSONObject data = request.getJSONObject("data");
		table_name = data.getString("table_name");
		int del_num = data.getInt("del_num");
		sql = new StringBuilder().append("DELTE FROM ").append(table_name).append(" where nid = ?").toString();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, del_num);
			row = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnect.discon();
		}
		response(row);
		return row;
	}

	// TODO SELECT
	private int selectOption(JSONObject data) {
		table_name = data.getString("table_name");
		int allORone = data.getInt("allORone");
		int select_num = data.getInt("select_num");
		int select_count = 0;
		String str_response = "";
		try {

			switch (allORone) {
			case 0 -> {
				sql = new StringBuilder("SELECT * FROM ").append(table_name).toString();
				pstmt = conn.prepareStatement(sql);
			}
			case 1 -> {
				sql = new StringBuilder("SELECT * FROM ").append(table_name).append(" where nid =  ?").toString();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, select_num);
			}
			}
			ResultSet rs = pstmt.executeQuery();
			
			selectWhile(rs,table_name);
					
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBConnect.discon();
			return select_count;
		}
	}

	private void selectWhile(ResultSet rs, String table_name) throws SQLException {
		JSONArray arr_respone = new JSONArray();
		int textCount = 0;
		while(rs.next()) {
			JSONObject response_data = new JSONObject();
			if(table_name.equals("Member")) {
				response_data.put("id", rs.getString("id"));
				response_data.put("name", rs.getString("name"));
				response_data.put("email", rs.getString("email"));
			}
			if(table_name.equals("Product")) {
				response_data.put("nid", rs.getString("nid"));
				response_data.put("name", rs.getString("name"));
				response_data.put("price", rs.getString("price"));
				response_data.put("content", rs.getString("content"));
			}
			if(table_name.equals("Board")) {
				response_data.put("nid", rs.getString("nid"));
				response_data.put("writer", rs.getString("writer"));
				response_data.put("title", rs.getString("title"));
				response_data.put("content", rs.getString("content"));
			}
			arr_respone.put(response_data);
			++ textCount;
		}
		
//		response(textCount,arr_respone);
	}
	
	
	
	// TODO UPDATE
	private int updateBoard(JSONObject data) {
		try {
			int nid = data.getInt("nid");
			String writer = data.getString("writer");
			String title = data.getString("title");
			String content = data.getString("content");
			sql = new StringBuilder()
					.append("UPDATE Product ")
					.append("SET ")
					.append("writer = ?,")
					.append("title = ?,")
					.append("content  = ? ")
					.append("where nid = ?")
					.toString();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.setInt(4, nid);

			row = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBConnect.discon();
			return row;
		}
	}

	private int updateProduct(JSONObject data) {
		try {
			int nid = data.getInt("nid");
			String name = data.getString("name");
			int price = data.getInt("price");
			String content = data.getString("content");
			sql = new StringBuilder()
					.append("UPDATE Product ")
					.append("SET ")
					.append("name = ?,")
					.append("price = ?,")
					.append("content  = ? ")
					.append("where nid = ?")
					.toString();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, price);
			pstmt.setString(3, content);
			pstmt.setInt(4, nid);

			row = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBConnect.discon();
			return row;
		}
	}

	private int updateMember(JSONObject data) {
		try {
			int nid = data.getInt("nid");
			String pwd = data.getString("pwd");
			String email = data.getString("email");
			int changeOption = data.getInt("changeOption");
			switch (changeOption) {
			case 1 -> {
				sql = new StringBuilder()
						.append("UPDATE Product ")
						.append("SET ")
						.append("pwd = ? ")
						.append("where nid = ?").toString();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, pwd);
				pstmt.setInt(2, nid);
			}
			case 2 -> {
				sql = new StringBuilder().append("UPDATE Product ")
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
			return row;
		}
	}

	// TODO insert
	// board문 insert
	public int insertBoard(JSONObject data) {
		int nid = data.getInt("nid");
		String writer = data.getString("writer");
		String title = data.getString("title");
		String content = data.getString("content");

		sql = new StringBuilder().append("INSERT INTO Board").append("(nid,writer,title,content) ")
				.append("values(?, ?, ?, ?)").toString();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nid);
			pstmt.setString(2, writer);
			pstmt.setString(3, title);
			pstmt.setString(4, content);

			row = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBConnect.discon();
			return row;
		}
	}

	// TODO insert
	// product문 insert
	public int insertProduct(JSONObject data) {
		try {
			int nid = data.getInt("nid");
			String name = data.getString("name");
			String pwd = data.getString("price");
			String content = data.getString("content");

			sql = new StringBuilder().append("INSERT INTO Product").append("(nid, name, price, content) ")
					.append("values(?, ?, ?, ?)").toString();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nid);
			pstmt.setString(2, name);
			pstmt.setString(3, pwd);
			pstmt.setString(4, content);

			row = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBConnect.discon();
			return row;
		}
	}

	// TODO insert
	// member문 insert
	public int insertMember(JSONObject data) {
		try {
			int nid = data.getInt("nid");
			String name = data.getString("name");
			String pwd = data.getString("pwd");
			String email = data.getString("email");

			sql = new StringBuilder().append("INSERT INTO Product").append("(nid, name, pwd, email) ")
					.append("values(?, ?, ?, ?)").toString();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nid);
			pstmt.setString(2, name);
			pstmt.setString(3, pwd);
			pstmt.setString(4, email);

			row = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBConnect.discon();
			return row;
		}
	}

}
