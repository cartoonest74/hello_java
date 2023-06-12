package hello_java.chatting;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.json.JSONObject;

public class SocketClient {
	ChatServer chatServer;
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	String clientIp;
	String chatName;
	
	public SocketClient(ChatServer chatServer, Socket socket) {
		try {
			this.chatServer = chatServer;
			this.socket = socket;
			this.dis = new DataInputStream(socket.getInputStream());
			this.dos = new DataOutputStream(socket.getOutputStream());
			InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
			this.clientIp = isa.getHostName();
			receive();
		}catch(IOException e) {}
			
	}

	private void receive() {
		chatServer.threadPool.execute(() ->{
			try {
				while(true) {
					String receiveJson = dis.readUTF();
					
					JSONObject jsonObject = new JSONObject(receiveJson);
					String command = jsonObject.getString("command");
					
					switch(command){
						case "incoming":
							this.chatName = jsonObject.getString("data");
							chatServer.sendToAll(this, "well come!");
							chatServer.addSocketClient(this);
							break;
						case "message":
							String message = jsonObject.getString("data");
							chatServer.sendToAll(this, message);
							break;
					}
				}
			}catch(IOException e) {
				chatServer.sendToAll(this, "out");
				chatServer.removeSocketClient(this);
			}
		});
	}
	
	//메소드: JSON 보내기
	public void send(String json) {
		try {
			dos.writeUTF(json);
			dos.flush();
		}catch(IOException e) {}
	}
	
	//메소드: 연결 종료
	public void close() {
		try {
			socket.close();
		}catch(Exception e) {}
	}
}
