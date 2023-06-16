package hello_java.accountReview;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class KpopClient {
	Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	private Scanner scanner = new Scanner(System.in);

	public KpopClient() {
	}

	public void start() throws IOException {
		socket = new Socket("localhost", 50001);
		System.out.println("client connection");

		dos = new DataOutputStream(socket.getOutputStream());
		dis = new DataInputStream(socket.getInputStream());
		list();
	}

	public void list() throws JSONException, IOException {
		
		JSONObject request = new JSONObject();
		request.put("menu", 0);
		request.put("data", new JSONObject());
		dos.writeUTF(request.toString());
		dos.flush();

		JSONObject response = new JSONObject(dis.readUTF());
		if (response.getString("status").equals("ok")) {
			System.out.println("--------------------------------------------------\n");
			System.out.println("no\tname\talbum\t\tTrackList\n");
			JSONArray data = response.getJSONArray("data");
			for (int i = 0; i < data.length(); i++) {
				JSONObject kpop = data.getJSONObject(i);

				String str_list = new StringBuilder()
						.append(kpop.getInt("no"))
						.append("\t").append(kpop.getString("name")).append("\t").append(kpop.getString("album"))
						.append("\t\t").append(kpop.getInt("trackList")).toString();

				System.out.println(str_list);
			}
		}
		;
		menu();
	}

	public void menu() {
		String str_menu = new StringBuilder().append("--------------------------------------------------\n")
				.append("no\tname\talbum\t\tTrackList\n")
				.append("--------------------------------------------------\n")
				.append("1.CREATE\t2.UPDATE\t3.DELETE\t4.EXIT\n")
				.append("--------------------------------------------------\n").append("SELECT NUM: ").toString();
		System.out.println(str_menu);
		int choice_num = scanner.nextInt();

		switch (choice_num) {
		case 1 -> create();
		case 2 -> update();
		case 3 -> delete();
		case 4 -> exit();
		}
	}

	private void create() {
		System.out.println("[CREATE]");
		
		System.out.print("NAME: ");
		scanner.nextLine();
		String create_name = scanner.nextLine();

		scanner.nextLine();
		System.out.print("ALBUM: ");
		String create_album = scanner.nextLine();

		scanner.nextLine();
		System.out.print("TRACKLIST: ");
		int create_trackList = Integer.parseInt(scanner.nextLine());

		JSONObject data = new JSONObject();
		data.put("name", create_name);
		data.put("album", create_album);
		data.put("trackList", create_trackList);

		toRequest(1, data);
		try {
			checkResponse();
		} catch (JSONException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	private void update() {
		System.out.println("[UPDATAE]");
		System.out.print("Edit no: ");
		int edit_no = scanner.nextInt();

		System.out.print("Edit_NAME: ");
		scanner.nextLine();
		String update_name = scanner.nextLine();

		scanner.nextLine();
		System.out.print("Edit_ALBUM: ");
		String update_album = scanner.nextLine();

		scanner.nextLine();
		System.out.print("Edit_TRACKLIST: ");
		int update_trackList = Integer.parseInt(scanner.nextLine());

		JSONObject data = new JSONObject();
		data.put("no", edit_no);
		data.put("name", update_name);
		data.put("album", update_album);
		data.put("trackList", update_trackList);

		toRequest(2, data);
		try {
			checkResponse();
		} catch (JSONException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	private void delete() {
		System.out.println("[DELETE]");
		System.out.println("Delete no: ");
		int del_no = scanner.nextInt();

		JSONObject data = new JSONObject();
		data.put("no", del_no);

		toRequest(3, data);

		try {
			checkResponse();
		} catch (JSONException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	private void toRequest(int menuNum, JSONObject data) {

		JSONObject request = new JSONObject();
		request.put("menu", menuNum);
		request.put("data", data);
		try {
			dos.writeUTF(request.toString());
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	private void checkResponse() throws JSONException, IOException {

		JSONObject response = new JSONObject(dis.readUTF());

		if (response.getString("status").equals("ok")) {
			list();
		} else {
			System.out.println("NOT FOUND DATA");
			menu();
		}
	}

	public void exit() {
		try {
			socket.close();
			System.out.println("client unconnection");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] arg) {
		KpopClient kc = new KpopClient();
			try {
				kc.start();
			} catch (IOException e) {
				kc.exit();
			}
	}
}
