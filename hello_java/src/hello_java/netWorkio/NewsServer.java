package hello_java.netWorkio;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.Scanner;

public class NewsServer {
	public static DatagramSocket datagramSocket = null;

	public static void main(String[] args) {
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
					datagramSocket = new DatagramSocket(50001);
					System.out.println("server start");

					while (true) {
						DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
						datagramSocket.receive(receivePacket);
						String newsKind = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");

						// 클라이언트의 IP와 PORT얻기
						SocketAddress socketAddress = receivePacket.getSocketAddress();

						// 10개의 뉴스를 클라이언트로 전송
						for (int i = 1; i <= 10; i++) {
							String data = newsKind + ": 뉴스" + i;
							byte[] bytes = data.getBytes("UTF-8");

						DatagramPacket sendPacket = new DatagramPacket(bytes, 0, bytes.length, socketAddress);
						datagramSocket.send(sendPacket);
						}
					}
				} catch (Exception e) {
					System.out.println("ㅅ버 : " + e.getMessage());
				}
			}
		};
		thread.start();
	}

	private static void stopServer() {
		// DatagramSocket 을 닫고 Port언바인딩
		datagramSocket.close();
		System.out.println("end");
	}

}
