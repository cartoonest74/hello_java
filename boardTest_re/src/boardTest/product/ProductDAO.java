package boardTest.product;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.sql.SQLException;

import org.json.JSONObject;

import boardTest.DBConn.DBConnect;
import boardTest.extend.BoardSuper;

public class ProductDAO extends BoardSuper{
	public ProductDAO() {
		super();
	}
	public ProductDAO (DataOutputStream dos, DataInputStream dis) {
		super(dos, dis);
	}
	// product문 insert
		public void insert(JSONObject request) {
			try {
				
				JSONObject data = request.getJSONObject("data");
				int nid = data.getInt("nid");
				String id = data.getString("id");
				String name = data.getString("name");
				String price = data.getString("price");
				String content = data.getString("content");

				sql = new StringBuilder().append("INSERT INTO Product").append("(id, nid, name, price, content) ")
						.append("values(?, ?, ?, ?, ?)").toString();

				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, id);
				pstmt.setInt(2, nid);
				pstmt.setString(3, name);
				pstmt.setString(4, price);
				pstmt.setString(5, content);

				row = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();

			} finally {
				DBConnect.discon();
				response(row, request);
			}
		}
		
		// product문 update
		public void update(JSONObject request) {
			try {
				JSONObject data = request.getJSONObject("data");
				int editNum = data.getInt("num");
				String name = data.getString("name").trim();
				int price = data.getInt("price");
				String content = data.getString("content").trim();
				sql = new StringBuilder()
						.append("UPDATE Product ")
						.append("SET ")
						.append("name = ?, ")
						.append("price = ?, ")
						.append("content  = ? ")
						.append("where num = ?")
						.toString();
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setInt(2, price);
				pstmt.setString(3, content);
				pstmt.setInt(4, editNum);

				row = pstmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();

			} finally {
				DBConnect.discon();
				response(row,request);
			}
		}

}