package hello_java.netWorkio;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class NewsClient {

	public static void main(String[] args) {
		try {
			DatagramSocket datagramSocket = new DatagramSocket();
			
			// 구독하고 싶으 뉴스 주제 보내기
			String data = "코믹";
			byte[] bytes = data.getBytes("UTF-8");
			DatagramPacket sendPacket = new DatagramPacket(bytes, bytes.length, new InetSocketAddress("localhost", 50001));
			datagramSocket.send(sendPacket);
			
			while(true) {
				// 뉴스 받기
				DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
				datagramSocket.receive(receivePacket);
				
				//문자열로 변환
				String news = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
				System.out.println(news);
				
				if(news.contains("뉴스10")) break;
			}
			//datagramSocket 닫기
			datagramSocket.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
