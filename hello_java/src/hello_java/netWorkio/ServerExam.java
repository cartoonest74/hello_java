package hello_java.netWorkio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerExam {
	public static ServerSocket serverSocket = null;

	public static void main(String[] args) {
//		try{
//			InetAddress ia = InetAddress.getLocalHost();
//			InetAddress[] iaArr = InetAddress.getAllByName("cartoonest74.com");
//			System.out.println(ia.getHostAddress());
//			for(InetAddress remote: iaArr) {
//				System.out.println(remote.getHostAddress());
//			}
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}

		System.out.println("-----------");
		System.out.println("server quit : q");
		System.out.println("-----------");

		startServer();

		Scanner sc = new Scanner(System.in);
		while (true) {
			String key = sc.nextLine();
			if (key.toLowerCase().equals("q")) {
				break;
			}
		}
		sc.close();

		stopServer();
	}

	private static void startServer() {
		Thread thread = new Thread() {
			public void run() {
				try {
					serverSocket = new ServerSocket();
					serverSocket.bind(new InetSocketAddress("localhost", 50001));
					System.out.println("start");
					Thread.sleep(3000);

					while (true) {
						System.out.println("\n wait \n");
						// 연결수락
						Socket socket = serverSocket.accept();

						// 연결된 클라이언트의 정보 얻기
						InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
						System.out.println(isa.getHostName() + " accpet ");

						// 데이터 받기
						DataInputStream dis = new DataInputStream(socket.getInputStream());
						String message = dis.readUTF();

						// 데이터 보내기
						DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
						dos.writeUTF(message);
						dos.flush();
						socket.close();
						System.out.println("받은 데이터를 다시 보냄: " + message);
						System.out.println(isa.getHostName() + " 연결을 끊음");
						System.out.println("end");
					}
				} catch (IOException e) {
					System.out.println("server: " + e.getMessage());
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					try {
						serverSocket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();
	}

	private static void stopServer() {
		try {
			serverSocket.close();
			System.out.println("end");
		} catch (IOException e) {

		}
	}

}
