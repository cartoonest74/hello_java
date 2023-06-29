package memberBoard.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import memberBoard.conn.DBConnect;
import memberBoard.member.MemberVo;

public class MemberBoardServer {
	private ServerSocket serverSocket;
	ExecutorService threadPool;
	public List<MemberVo> arr_member;
	private Connection conn = DBConnect.getConnection();
	private MemberVo mv = new MemberVo();
	private PreparedStatement pstmt;
	private int nowCount;
	private int preCount;

	public void start() throws IOException {

		serverSocket = new ServerSocket(50002);
		threadPool = Executors.newFixedThreadPool(100);
		arr_member = Collections.synchronizedList(new ArrayList<>());
		System.out.println("server start");

		Thread thread = new Thread(() -> {
			boolean flag = true;
			while (flag) {
				Socket socket;
				try {
					previousArr();
					socket = serverSocket.accept();
					MemberBoardClient mbc = new MemberBoardClient(this, socket);
				} catch (IOException e) {
					break;
				}
			}
		});

		thread.start();
	}

	// db에 있는 데이터 배열에 미리 넣기
	private void previousArr() {
		ResultSet rs;
		String sql;
		try {
			// count
			sql = "SELECT count(*) count FROM Member";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			// 현재접속시점에 db row개수 값
			if(rs.next()) {
				nowCount = rs.getInt("count");
			}
			
			// 현재접속시점에 db row 개수와 전 접속시점에 db row 개수 비교후 배열업데이트 하기
			if(nowCount !=  preCount) {
				sql = "Select * from Member where nid > ? and id <= ?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, preCount);
				pstmt.setInt(2, nowCount);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					mv.setId(rs.getString("id"));
					mv.setName(rs.getString("name"));
					mv.setPwd(rs.getString("pwd"));
					mv.setEmail(rs.getString("email"));
					arr_member.add(mv);
				}
			}			
			rs.close();
			
			// 과정이 끝나면 전접속 시점에 개수와 현재 접속시점에 개수 일치화
			preCount = nowCount;
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void stop() {
		try {
			serverSocket.close();
			threadPool.shutdown();
			System.out.println("server TheEnd");
		} catch (IOException e) {
			e.printStackTrace();

		}
	}
}
