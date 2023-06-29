package memberBoard.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.json.JSONObject;

import memberBoard.conn.DBConnect;
import memberBoard.member.MemberDao;
import memberBoard.member.MemberVo;

public class MemberBoardClient {
	private DataOutputStream dos;
	private DataInputStream dis;
	private MemberBoardServer meberBoardServer;
	private Socket socket;
	private MemberDao md;
	private Connection conn = DBConnect.getConnection();
	private MemberVo mv = new MemberVo();
	private List<MemberVo> arr_member;
	
	public MemberBoardClient(MemberBoardServer mbs, Socket socket) {
		try {
			this.meberBoardServer = mbs;
			this.socket = socket;
			this.dos = new DataOutputStream(socket.getOutputStream());
			this.dis = new DataInputStream(socket.getInputStream());
			this.arr_member = mbs.arr_member;
			md = new MemberDao(dos, dis, arr_member);
			
			
			receive();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void close() {
		try {
			socket.close();
			System.out.println("Socket Close");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void receive() {
		meberBoardServer.threadPool.execute(()->{
			boolean flag = true;
			while(flag) {
				String receiveJson;
				try {
					receiveJson = dis.readUTF();
					JSONObject request = new JSONObject(receiveJson);
					int first_menu = request.getInt("first_menu");
					int second_menu = request.getInt("second_menu");
					if(first_menu == 1) {
						switch(second_menu) {
						case 0 -> md.insert(request); 
						case 1 -> md.login(request); 
						case 2 -> md.update(request); 
						case 3 -> md.delete(request); 
						case 4 -> md.logout(request); 
						}
					}
				
				} catch (IOException e) {
					close();
					break;
				
				}
			}
		});
	}
	
	public static void main(String[] arg) {
		MemberBoardServer mbs = new MemberBoardServer();
		try {
			mbs.start();
			System.out.println("----------------");
			System.out.println("server quit : q");
			System.out.println("----------------");
			
			Scanner sc = new Scanner(System.in);
			boolean flag = true;
			while (flag) {
				String str_q = sc.nextLine();
				if (str_q.toLowerCase().equals("q"))
					break;
			}
			mbs.stop();
		} catch (IOException e) {
			e.printStackTrace();
			mbs.stop();
		}

	}
}
