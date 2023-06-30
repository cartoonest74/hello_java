package hello_java.jenericT;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Select.jdbc.DBconn;

public class Member <T>{
	private String name;
	private String age;
	public Member() {};
	
	private Connection conn = DBconn.getConnection();
	
	public Member(String name, String age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	public void insert(T t1) {
		String sql = new StringBuilder()
					.append("SELECT * FROM ")
					.append(t1)
					.toString();
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getString("name"));
			}
		rs.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}finally {
			DBconn.discon();
		}
	}

	@Override
	public String toString() {
		return "Member [name=" + name + ", age=" + age + "]";
	}
}
