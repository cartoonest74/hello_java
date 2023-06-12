package hello_java.netWorkio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class EchoClient {
	public static void main(String[] arg) {
		try {
			Socket socket = new Socket("localhost", 50001);
			
			// 데이터 보내기
			System.out.println("cleint success");
			String sendMessage = "java daiski";
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(sendMessage);
			dos.flush();
			System.out.println("보냄 "+ sendMessage);
			
			//데이터 받기
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			String receiveMessage = dis.readUTF();
			System.out.println("데이터 받음" + receiveMessage);
			
			//연결 끊기
			socket.close();
			System.out.println("end");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
