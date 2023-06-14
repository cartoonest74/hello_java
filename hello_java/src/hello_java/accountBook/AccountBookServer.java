package hello_java.accountBook;

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

public class AccountBookServer {
	ServerSocket serverSocket;
	ExecutorService threadPool;
	List<Books> arr_books;
	boolean rm_check;

	public void start() throws IOException {
		serverSocket = new ServerSocket(50001);
		threadPool = Executors.newFixedThreadPool(100);
		arr_books = Collections.synchronizedList(new ArrayList<>());
		
		System.out.println("server start");
		
		while (true) {
			Socket socket = serverSocket.accept();
			SocketClient socketClient = new SocketClient(socket);
		}
	}

	public void stop() {
		try {
			serverSocket.close();
			threadPool.shutdown();
			System.out.println("server Theend");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public class SocketClient {
		private Socket socket;
		private DataOutputStream dos;
		private DataInputStream dis;
		private int sequence;

		public SocketClient(Socket socket) {
			try {
				this.socket = socket;
				this.dos = new DataOutputStream(socket.getOutputStream());
				this.dis = new DataInputStream(socket.getInputStream());
				recieve();
			} catch (IOException e) {
				e.printStackTrace();
				close();
			}
		}

		public void recieve() {
			threadPool.execute(() -> {
				try {
					while (true) {
						String receiveJson = dis.readUTF();
						JSONObject request = new JSONObject(receiveJson);
						int int_menu = request.getInt("menu");
						switch (int_menu) {
						case 0 : 
							allList(request);
						break;
						case 1 : 
							create(request);
						break;
						case 2:
							update(request);
						break;
						case 3:
							delete(request);
						break;
						}
					}
				} catch (IOException e) {
					close();
				}
			});
		}

		public void allList(JSONObject request) {
			try {
				JSONArray data = new JSONArray();
				for (Books book : arr_books) {
					JSONObject bookData = new JSONObject();
					bookData.put("no", book.getpNo());
					bookData.put("name", book.getpName());
					bookData.put("price", book.getpPrice());
					bookData.put("stock", book.getpStock());
					data.put(bookData);
				}

				JSONObject response = new JSONObject();
				response.put("status", "ok");
				response.put("data", data);
				dos.writeUTF(response.toString());
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		public void create(JSONObject request) {
			sequence++;
			JSONObject data = request.getJSONObject("data");
			Books books = new Books();
			books.setpNo(sequence);
			books.setpName(data.getString("name"));
			books.setpPrice(data.getInt("price"));
			books.setpStock(data.getInt("stock"));
			arr_books.add(books);

			JSONObject response = new JSONObject();
			response.put("status", "ok");
			response.put("data", new JSONObject());
			String str_response = response.toString();
			try {
				dos.writeUTF(str_response);
				dos.flush();
			} catch (IOException e) {
				System.out.println("not create");
			}

		}

		public void update(JSONObject request) {
			JSONObject data = request.getJSONObject("data");
			int edit_num = data.getInt("no");
			for (Books book : arr_books) {
				if (book.getpNo() == edit_num) {
					book.setpName(data.getString("name"));
					book.setpPrice(data.getInt("price"));
					book.setpStock(data.getInt("stock"));
				}
			}

			JSONObject response = new JSONObject();
			response.put("status", "ok");
			response.put("data", new JSONObject());
			String str_response = response.toString();
			try {
				dos.writeUTF(str_response);
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void delete(JSONObject request) {
			JSONObject data = request.getJSONObject("data");
			int del_num = data.getInt("no");
			Iterator<Books> iterator = arr_books.iterator();
			while (iterator.hasNext()) {
				Books book = iterator.next();
				if (book.getpNo() == del_num)
					iterator.remove();
			}
			
			JSONObject response = new JSONObject();
			response.put("status", "ok");
			response.put("data", new JSONObject());
			String str_response = response.toString();
			try {
				dos.writeUTF(str_response);
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void close() {
			try {
				socket.close();
				System.out.println("socketClient close");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] arg) {
		AccountBookServer accountBookServer = new AccountBookServer();
		try {
			accountBookServer.start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			accountBookServer.stop();
		}
	}
}
