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

	public void start() {
		try {
			socket = new Socket("localhost", 50001);
			System.out.println("client connection");

			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			list();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void list() throws JSONException, IOException {

		JSONObject request = new JSONObject();
		request.put("menu", 0);
		request.put("data", new JSONObject());
		dos.writeUTF(request.toString());
		dos.flush();

		JSONObject response = new JSONObject(dis.readUTF());
		if (response.getString("status").equals("ok")) {
			JSONArray data = response.getJSONArray("data");
			for (int i = 0; i < data.length(); i++) {
				JSONObject kpop = data.getJSONObject(i);

				String str_list = new StringBuilder().append("--------------------------------------------------")
						.append("no\tname\t\talbum\\t\\tTrackList\n")
						.append("--------------------------------------------------").append(kpop.getInt("no"))
						.append("\t").append(kpop.getString("name")).append("\t\t").append(kpop.getString("album"))
						.append("\t\t").append(kpop.getInt("trackList")).toString();

				System.out.println(str_list);
			}
		}
		;
		menu();
	}

	public void menu() {
		String str_menu = new StringBuilder()
				.append("--------------------------------------------------")
				.append("no\tname\t\talbum\\t\\tTrackList\n")
				.append("--------------------------------------------------")
				.append("1.CREATE\t2.UPDATE\t3.DELETE\t4.DELETE\n")
				.append("--------------------------------------------------")
				.append("번호를 선택: ")
				.toString();
		System.out.println(str_menu);
		int choice_num = scanner.nextInt();
		
		switch(choice_num) {
		case 1->create();
		case 2->update();
		case 3->delete();
		case 4->exit();
		}
	}
	private void create() {
		System.out.println("[CREATE]");
		System.out.print("NAME: ");
		scanner.nextLine();
		String create_name = scanner.nextLine();
		
		System.out.print("ALBUM: ");
		scanner.nextLine();
		int create_album = Integer.parseInt(scanner.nextLine());
		
		System.out.print("TRACKLIST: ");
		scanner.nextLine();
		int create_trackList = Integer.parseInt(scanner.nextLine());
		
		JSONObject data = new JSONObject();
		data.put("name", create_name);
		data.put("ablum", create_album);
		data.put("trakList", create_trackList);
	}


	private void update() {
		System.out.println("[UPDATAE]");
		System.out.print("Edit no:");
		int edit_no = scanner.nextInt();
		
		JSONObject request = new JSONObject();
		
//		if()
		
		System.out.print("Edit_NAME: ");
		scanner.nextLine();
		String update_name = scanner.nextLine();
		
		System.out.print("Edit_ALBUM: ");
		scanner.nextLine();
		int update_name_album = Integer.parseInt(scanner.nextLine());
		
		System.out.print("Edit_TRACKLIST: ");
		scanner.nextLine();
		int update_name_trackList = Integer.parseInt(scanner.nextLine());
		
	}

	private void delete() {
		
	}

	public void exit() {
		try {
			socket.close();
			System.out.println("client unconnection");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
