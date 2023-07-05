package boardTest.board;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.sql.SQLException;

import org.json.JSONObject;

import boardTest.DBConn.DBConnect;
import boardTest.extend.BoardSuper;

public class BoardDAO extends BoardSuper{
	
	public BoardDAO() {
		super();
	}
	
	public BoardDAO(DataOutputStream dos, DataInputStream dis) {
		super(dos, dis);
	}
	
	// board문 insert
	public void insert(JSONObject request) {
		JSONObject data = request.getJSONObject("data");
		String id = data.getString("id");
		int nid = data.getInt("nid");
		String writer = data.getString("writer");
		String title = data.getString("title");
		String content = data.getString("content");

		sql = new StringBuilder().append("INSERT INTO Board")
				.append("(id, nid, writer, title, content) ")
				.append("values(?, ?, ?, ?, ?)")
				.toString();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, nid);
			pstmt.setString(3, writer);
			pstmt.setString(4, title);
			pstmt.setString(5, content);

			row = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBConnect.discon();
			response(row, request);
		}
	}

	// board문 update
		public void update(JSONObject request) {
			try {
				JSONObject data = request.getJSONObject("data");
				int editNum = data.getInt("num");
				String writer = data.getString("writer");
				String title = data.getString("title");
				String content = data.getString("content");
				sql = new StringBuilder()
						.append("UPDATE Board ")
						.append("SET ")
						.append("writer = ?, ")
						.append("title = ?, ")
						.append("content  = ? ")
						.append("where num = ?")
						.toString();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, writer);
				pstmt.setString(2, title);
				pstmt.setString(3, content);
				pstmt.setInt(4, editNum);

				row = pstmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();

			} finally {
				DBConnect.discon();
				response(row, request);
			}
		}
}