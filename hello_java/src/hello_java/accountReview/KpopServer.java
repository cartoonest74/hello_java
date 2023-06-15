package hello_java.accountReview;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONArray;
import org.json.JSONObject;

public class KpopServer {
	ServerSocket serverSocket;
	List<Kpop> arr_kpop;
	ExecutorService threadPool;
	public void start() throws IOException {
		serverSocket = new ServerSocket(50001);
		threadPool = Executors.newFixedThreadPool(100);
		arr_kpop = Collections.synchronizedList(new ArrayList<>());
		System.out.println("sever start");
		
		while(true) {
			Socket socket = serverSocket.accept();
			SocketClient socketClient = new SocketClient(socket);
		}
	}
	
	public void stop(){
		try {
			serverSocket.close();
			threadPool.shutdown();
			System.out.println("server TheEnd");
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}
	
	public class SocketClient{
		private Socket socket;
		private DataOutputStream dos;
		private DataInputStream dis;
		private int Sequence;
		
		public SocketClient(Socket socket) {
			this.socket = socket;
			try {
				this.dos = new DataOutputStream(socket.getOutputStream());
				this.dis = new DataInputStream(socket.getInputStream());
				receive();
			} catch (IOException e) {
				e.printStackTrace();
				close();
			}
		}

		private void close() {
			try {
				socket.close();
				System.out.println("serverSocket close");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}

		private void receive() {
			threadPool.execute(()->{
				 while(true) {
					 String receiveStr;
					try {
						receiveStr = dis.readUTF();
						JSONObject request = new JSONObject(receiveStr);
						int menu_int = request.getInt("menu");
						switch(menu_int){
							case 0 -> list();
							case 1 -> create(request);
							case 2 -> update(request);
							case 3 -> delete(request);
						}
					} catch (IOException e) {
						close();
						e.printStackTrace();
						
					}
				 }
			});
		}

		private void list() {
			JSONArray data = new JSONArray();
			for(Kpop kpop: arr_kpop) {
				JSONObject kpop_data = new JSONObject();
				kpop_data.put("no", kpop.getNo());
				kpop_data.put("name", kpop.getName());
				kpop_data.put("album", kpop.getAlbum());
				kpop_data.put("trackList", kpop.getTrackList());
				 data.put(kpop_data);
			}
			
			JSONObject response = new JSONObject();
			response.put("status", "ok");
			response.put("data", data);
			try {
				dos.writeUTF(response.toString());
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
				
			}
			
		}

		private void create(JSONObject request) {
			Sequence ++;
			JSONObject data = request.getJSONObject("data");
			Kpop kpop = new Kpop();
			kpop.setNo(Sequence);
			kpop.setName(data.getString("name"));
			kpop.setAlbum(data.getString("album"));
			kpop.setTrackList(data.getInt("trackList"));
			arr_kpop.add(kpop);
			
			JSONObject response = new JSONObject();
			response.put("status", "ok");
			response.put("data", data);
			try {
				dos.writeUTF(response.toString());
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
				
			}
		}

		private void update(JSONObject request) {
		}
		
		private void delete(JSONObject request) {
		}
		
	}
}
