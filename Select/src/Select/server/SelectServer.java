package Select.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Select.service.MemberDAO;
import Select.vo.Member;

public class SelectServer {
	ServerSocket serverSocket;
	ExecutorService threadPool;
	public List<Member> arr_member;

	public void start() throws IOException {
		serverSocket = new ServerSocket(50001);
		threadPool = Executors.newFixedThreadPool(100);
		arr_member = Collections.synchronizedList(new ArrayList<>());
		
		System.out.println("server start");

		Thread thread = new Thread(() -> {
			while (true) {
				try {
					Socket socket = serverSocket.accept();
					SocketClient socketClient = new SocketClient(this, socket);
				} catch (IOException e) {}
			}
		});

		thread.start();
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
