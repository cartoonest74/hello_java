package hello_java.accountReview;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
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

		Thread thread = new Thread(()->{
			while (true) {
				try {
					Socket socket = serverSocket.accept();
					SocketClient socketClient = new SocketClient(socket);
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

	public class SocketClient {
		private Socket socket;
		private DataOutputStream dos;
		private DataInputStream dis;
		private int Sequence;

		public SocketClient(Socket socket) {
			try {
				this.socket = socket;
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
			threadPool.execute(() -> {
				while (true) {
					String receiveStr;
					try {
						receiveStr = dis.readUTF();
						JSONObject request = new JSONObject(receiveStr);
						int menu_int = request.getInt("menu");
						switch (menu_int) {
						case 0 -> list();
						case 1 -> create(request);
						case 2 -> update(request);
						case 3 -> delete(request);
						}
					} catch (IOException e) {
						close();
						break;
					}
				}
			});
		}

		private void list() {
			JSONArray data = new JSONArray();
			for (Kpop kpop : arr_kpop) {
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
			Sequence++;
			JSONObject data = request.getJSONObject("data");
			Kpop kpop = new Kpop();
			kpop.setNo(Sequence);
			kpop.setName(data.getString("name"));
			kpop.setAlbum(data.getString("album"));
			kpop.setTrackList(data.getInt("trackList"));
			arr_kpop.add(kpop);

			response("ok", new JSONObject());
		}

		private void update(JSONObject request) {
			JSONObject data = request.getJSONObject("data");
			int no_data = data.getInt("no");
			String updateCkeck = "no";
			for (Kpop kpops : arr_kpop) {
				if (kpops.getNo() == no_data) {
					kpops.setName(data.getString("name"));
					kpops.setAlbum(data.getString("album"));
					kpops.setTrackList(data.getInt("trackList"));
					updateCkeck = "ok";
				}
			}
			response(updateCkeck, new JSONObject());

		}

		private void delete(JSONObject request) {
			JSONObject data = request.getJSONObject("data");
			boolean removeCheck = false;
			int rdel_no = data.getInt("no");
			Iterator<Kpop> iter_kpop = arr_kpop.iterator();
			while (iter_kpop.hasNext()) {
				Kpop gdel_no = iter_kpop.next();
				if (gdel_no.getNo() == rdel_no) {
					removeCheck = true;
					iter_kpop.remove();
					response("ok", new JSONObject());
				}
			}
			if (!removeCheck)
				response("no", new JSONObject());
		}

		private void response(String status, JSONObject jso) {
			JSONObject response = new JSONObject();
			response.put("status", status);
			response.put("data", jso);
			try {
				dos.writeUTF(response.toString());
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();

			}
		}
	}

	public static void main(String[] arg) throws IOException {
		KpopServer ks = new KpopServer();
		ks.start();
		
		System.out.println("----------------");
		System.out.println("server quit : q");
		System.out.println("----------------");
		
		Scanner sc = new Scanner(System.in);
		while (true) {
			String str_q = sc.nextLine();
			if (str_q.toLowerCase().equals("q"))
				break;
		}
		sc.close();
		ks.stop();
	}
}
