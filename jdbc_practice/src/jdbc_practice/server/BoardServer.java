package jdbc_practice.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONException;
import org.json.JSONObject;

import jdbc_practice.vo.Boards;

public class BoardServer {
	ServerSocket serverSocket;
	List<Boards> board;
	ExecutorService threadPool;
	public void start() {
		try {
			board = Collections.synchronizedList(new ArrayList<>());
			threadPool = Executors.newFixedThreadPool(100);
			serverSocket = new ServerSocket(50001);
			
			System.out.println("server start");
			
			Thread thread = new Thread(()->{
				while(true) {
					try {
						Socket socket = serverSocket.accept();
						SocketClient socketClient = new SocketClient(socket);
					} catch (IOException e) {
						stop();
						System.out.println("accept fail");
						break;
					}
				}
				
			});
			thread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public class SocketClient{
		Socket socket;
		DataOutputStream dos;
		DataInputStream dis;
		Connection conn = null;
		public SocketClient(Socket socket) {
			this.socket = socket;
			try {
				this.dis = new DataInputStream(socket.getInputStream());
				this.dos = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
				
			}
		}
		
		public void receive() {
			threadPool.execute(()->{
				try {
					while(true) {
						String str_receive = dis.readUTF();
						JSONObject request = new JSONObject(str_receive);
						int menu_num = request.getInt("menu");
						switch(menu_num) {
						case 0  -> list();
						case 1  -> create(request);
						case 2  -> update(request);
						case 3 -> read(request);
						case 4 -> clear(request);
						case 5 -> exit(request);
						
						}
					}
					
				} catch (IOException e) {
					e.printStackTrace();
					
				}
				
			});
		}
		
		private void connection() {
			try {
				Class.forName("oracle.jdbc.OracleDriver");
				conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521/orcl",
						"java",
						"oracle");
			
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}

		private void connClose() {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
			}
		}
		
		// TODO fileValidation
		private	File fileValidation(String fpath) {
			File file = new File(fpath);
			if(file.exists()) {
				return file;
			}else {
				return file = new File("C:/Temp/img1.jpg");
			}
		}
		
		// TODO response
		private void response(String status, JSONObject jso) {
			JSONObject response = new JSONObject();
			response.put("status", status);
			response.put("data", jso);
			try {
				dos.writeUTF(response.toString());
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();

			}
		}
		// TODO SELECT
		private void all_select(int bno) {
			connection();
			try {
				Boards boards = new Boards();
				String sql = "SELECT * FROM boards WHERE bno = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				pstmt.setInt(1, bno);
				
				while(rs.next()) {
					boards.setBno(rs.getInt("bno"));
					boards.setBfilename(rs.getString("bfilename"));
					boards.setBtitle(rs.getString("btitle"));
					boards.setBcontent(rs.getString("bcontent"));
					boards.setBwrite(rs.getString("bwrite"));
					boards.setBdate(rs.getDate("bdate"));
					boards.setBfilename(rs.getString("bfilename"));
					boards.setBfiledate(rs.getBlob("bfiledate"));
					
					// list put boards obj
					board.add(boards);
				}
			} catch (SQLException e) {
				e.printStackTrace();

			}
			connClose();
		}
		
		private void list() {
			JSONObject data = new JSONObject();
			for(Boards boards:board) {
				boards.getBno();
			}
			
		}

		private void create(JSONObject request) {
			JSONObject data = request.getJSONObject("data");
			connection();
			String sql = "INSERT INTO boards(bno, btitle, bcontent, bwrite, bdate, bfilename, bfiledate) "
					+ "VALUES (SEQ_BNO, ?, ?, ?, SYSDATE, ?, ?)";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,data.getString("title"));
				pstmt.setString(2,data.getString("content"));
				pstmt.setString(3,data.getString("write"));
				pstmt.setString(4,data.getString("filename"));
				pstmt.setBlob(5,new FileInputStream(fileValidation(data.getString("filedate"))));
			
				int rows = pstmt.executeUpdate();
				System.out.println("saved rows: " + rows);
			} catch (SQLException e) {
				e.printStackTrace();
			
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				
			} catch (JSONException e) {
				e.printStackTrace();
				
			}
			connClose();
			
			response("ok", new JSONObject());
		}

		private void update(JSONObject request) {
			
		}
		private void read(JSONObject request) {
			
		}
		private void clear(JSONObject request) {
			
		}
		private void exit(JSONObject request) {
			
		}
	}
	
	public static void main(String[] arg) {
		BoardServer boardServer = new BoardServer();
		try {
			boardServer.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("quit: q");
			while(true) {
				if(br.readLine().toLowerCase().equals("q")) {
					boardServer.stop();
					System.out.println("stop");
				}
			}
		}catch(Exception e) {
			boardServer.stop();
		}
	}
}
