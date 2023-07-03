package boardTest.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONObject;

import boardTest.board.BoardDAO;
import boardTest.extend.BoardSuper;
import boardTest.main.Client;
import boardTest.member.LoginDTO;
import boardTest.member.MemberDAO;
import boardTest.product.ProductDAO;

public class ServerBT {
	private ServerSocket serverSocket;
	private ExecutorService threadPool;
	public Map<String, SocketClient> mapLogin = Collections.synchronizedMap(new HashMap<>());
	List<BoardSuper> boardSuper_childs;
	public static LoginDTO loginDTO = new LoginDTO();
	String loginId = "";
	int loginIf = 0;
	
	private void start() throws IOException {
		serverSocket = new ServerSocket(50001);
		threadPool = Executors.newFixedThreadPool(100);
		System.out.println("server start");
		
		boolean flag = true;
		Thread thread = new Thread(() -> {
			while (true) {
				Socket socket;
				try {
					socket = serverSocket.accept();
					SocketClient scbt = new SocketClient(this, socket);
				} catch (IOException e) {}
				
			}
		});
		thread.start();
	}

	private void stop() {
		try {
			serverSocket.close();
			threadPool.shutdown();
			System.out.println("server close");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addLoginSocketClient(String key,SocketClient socketClient) {
		mapLogin.put(key, socketClient);
	}
	
	public class SocketClient {
		private DataOutputStream dos;
		private DataInputStream dis;
		private Socket socket;
		private ServerBT sbt;
		private String className;
		public SocketClient(ServerBT sbt, Socket socket) {
			try {
				this.sbt = sbt;
				this.socket = socket;
				this.dos = new DataOutputStream(socket.getOutputStream());
				this.dis = new DataInputStream(socket.getInputStream());
				
				boardSuper_childs = new ArrayList<>(); 
				boardSuper_childs.add(new MemberDAO(dos, dis));
				boardSuper_childs.add(new ProductDAO(dos, dis));
				boardSuper_childs.add(new BoardDAO(dos, dis));

				recieve();
			} catch (IOException e) {
				e.printStackTrace();
				close();
			}
		}

		private void recieve() {
			threadPool.execute(() -> {
				boolean flag = true;
				while (flag) {
					String receivejs;
					try {
						receivejs = dis.readUTF();
						JSONObject data = new JSONObject(receivejs);
						data.put("loginId", loginId);
						data.put("loginIf", loginIf);

						int menuNum = data.getInt("menuNum");

						switch (menuNum) {
						case 0 -> login(data);
						case 1 -> insert(data);
						case 2 -> select(data);
						case 3 -> update(data);
						case 4 -> delete(data);
						case 5 -> logout(data);
						default -> close();
						}
					} catch (IOException e) {
						e.printStackTrace();
						
						break;
					}
				}
			});
		}
		
		private void logout(JSONObject data) {
			// LoginDTO setLoginIf 로그아웃 처리
			int logOut = 0;
			loginDTO.setLoginIf(logOut);
			
			loginId = loginDTO.getLoginId();
			loginIf = loginDTO.getLoginIf();
			
			// mapLogin 해당 userId(key) 로그인 기록 remove 
			mapLogin.remove(loginId);
			
			data.put("loginId", loginId);
			data.put("loginIf", loginIf);

			int memberDAO = 0;
			boardSuper_childs.get(memberDAO).logout(data);
		}

		private void login(JSONObject data) {
			JSONObject logindata = data.getJSONObject("data");
			String saveLoginId = logindata.getString("id");
			addLoginSocketClient(saveLoginId, this);
			SocketClient loginExists = mapLogin.get(saveLoginId);
			
			
			// LoginDTO setLoginIf 로그인 처리
			int memberDAO = 0;
			int loginOk = 1;
			
			
			//  login map에 UserID(KEY)에 값이 있을경우 로그인처리
			if(loginExists != null) {
				
				loginDTO.setLoginIf(loginOk);
				loginDTO.setLoginId(saveLoginId);

				loginId = loginDTO.getLoginId();
				loginIf = loginDTO.getLoginIf();
				data.put("loginId", loginId);
				data.put("loginIf", loginIf);
				
				boardSuper_childs.get(memberDAO).login(data);  
			
			}
		}

		private void close() {
			try {
				socket.close();
				System.out.println("severSocketClient close");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		private void insert(JSONObject data) {
			className = data.getString("className");
			for(BoardSuper childs:boardSuper_childs) {
				if(childs.getClass().getSimpleName().equals(className))
					childs.insert(data);
			}
		}

		private void select(JSONObject data) {
			className = data.getString("className");
			for(BoardSuper childs:boardSuper_childs) {
				if(childs.getClass().getSimpleName().equals(className))
					childs.select(data);
			}
		}

		private void update(JSONObject data) {
			className = data.getString("className");
			for(BoardSuper childs:boardSuper_childs) {
				if(childs.getClass().getSimpleName().equals(className))
					childs.update(data);
			}
		}

		private void delete(JSONObject data) {
			className = data.getString("className");
			for(BoardSuper childs:boardSuper_childs) {
				if(childs.getClass().getSimpleName().equals(className))
					childs.delete(data);
			}
		}

	}
	public static void main(String[] arg) throws IOException {
		ServerBT serverBT = new ServerBT();
		serverBT.start();
		System.out.println("------------------------");
		System.out.println("server quit: q");
		System.out.println("------------------------");
		Boolean flag = true;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(flag) {
			if(br.readLine().equals("q"))
				break;
		}
		br.close();
		serverBT.stop();
	}
}