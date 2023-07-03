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
		}
	}

	// board문 update
		public void update(JSONObject request) {
			try {
				JSONObject data = request.getJSONObject("data");
				int nid = data.getInt("nid");
				String writer = data.getString("writer");
				String title = data.getString("title");
				String content = data.getString("content");
				sql = new StringBuilder()
						.append("UPDATE Board ")
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
			}
		}
}
