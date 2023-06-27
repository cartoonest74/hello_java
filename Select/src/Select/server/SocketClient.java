package Select.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import org.json.JSONObject;

import Select.service.MemberDAO;

public class SocketClient {
	SelectServer selectServer;
	MemberDAO dao;
	private Socket socket;
	public DataOutputStream dos;
	public DataInputStream dis;
	
	public SocketClient() {}
	public SocketClient(SelectServer selectServer, Socket socket) {
		try {
			dao = new MemberDAO();
			this.socket = socket;
			this.selectServer = selectServer;
			this.dos = new DataOutputStream(socket.getOutputStream());
			this.dis = new DataInputStream(socket.getInputStream());
			recieve();
		} catch (IOException e) {
			e.printStackTrace();
			close();
		}
	}
	
	private void close() {
		try {
			socket.close();
			System.out.println("socketClient close");
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	private void recieve() {
		selectServer.threadPool.execute(() -> {
			while (true) {
			try {
					String receiveJson = dis.readUTF();
					JSONObject request = new JSONObject(receiveJson);
					int int_menu = request.getInt("menu");
					switch (int_menu) {
					case 0 -> dao.selectAll(request);
					case 1 -> dao.insert(request);
					case 2 -> dao.select(request);
					case 3 -> dao.update(request);
					case 4 -> dao.delete(request);
					}
				} catch (Exception e) {
					close();
					break;
				}
			}
		});
	}

	public static void main(String[] arg) throws IOException {
		SelectServer selectServer = new SelectServer();
		selectServer.start();

		System.out.println("----------------");
		System.out.println("server quit : q");
		System.out.println("----------------");

		Scanner sc = new Scanner(System.in);
		while (true) {
			String str_q = sc.nextLine();
			if (str_q.toLowerCase().equals("q"))
				break;
		}
		selectServer.stop();
	}
}
