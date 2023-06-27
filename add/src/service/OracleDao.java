package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.Member;

public class OracleDao implements Dao {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public void con() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 드라이버 로드
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr"); // connection객체생성
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void discon() { // close메서드 insert,update을 사용하면 호출
		try {
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void insert(Member m) {
		// TODO Auto-generated method stub
		con();
		String sql = "insert into addr values(?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getName());
			pstmt.setString(2, m.getTel());
			pstmt.setString(3, m.getAddress());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}discon();
	}

	@Override
	public ArrayList<Member> selectAll() {
		// TODO Auto-generated method stub
		con();
		ArrayList<Member> list = new ArrayList<Member>();
		ResultSet rs = null;
		String sql = "select * from addr";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Member(rs.getString("name"), rs.getString(2), rs
						.getString(3)));
			}
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		discon();
		return list;
	}

	@Override
	public Member select(String name) {
		// TODO Auto-generated method stub
		con();
		Member m = null;
		ResultSet rs = null;
		String sql = "select * from addr where name=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				m = new Member(rs.getString("name"), rs.getString(2),
						rs.getString(3));
			}
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		discon();
		return m;
	}

	@Override
	public boolean update(Member m) {
		// TODO Auto-generated method stub
		con();
		String sql = "update addr set tel=?, address=? where name=?";
		int num = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getTel());
			pstmt.setString(2, m.getAddress());
			pstmt.setString(3, m.getName());
			num = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		discon();
		if (num <= 0) {
			return false;
		} else {
			return true;
		} 
	}

	@Override
	public boolean delete(String name) {
		// TODO Auto-generated method stub
		con();
		
		String sql = "delete addr where name=?";
		int num = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			num = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		discon();
		if (num <= 0) {
			return false;
		} else {
			return true;
		}
	}

}
