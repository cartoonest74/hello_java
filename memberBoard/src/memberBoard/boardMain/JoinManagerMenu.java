package memberBoard.boardMain;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.json.JSONObject;

import memberBoard.exception.CheckFormatException;

public class JoinManagerMenu {
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public JoinManagerMenu(Socket socket, DataOutputStream dos, DataInputStream dis) {
		this.socket = socket;
		this.dos = dos;
		this.dis = dis;
	}

	public void join_menu() {
		String join_menu = new StringBuilder()
				.append("---------------------------------------------------------------------\n")
				.append("1.JOIN 2.LOGIN 3.MYINFO 4.EDIT 5.LOGOUT 6.DELETE 7.BACK_MENU\n")
				.append("---------------------------------------------------------------------\n")
				.append("SELECT NUM: ").toString();
		System.out.println(join_menu);
		int choice_num;
		try {
			choice_num = Integer.parseInt(br.readLine());

			switch (choice_num) {
			case 1 -> insert(); // join
			case 2 -> login();
			case 3 -> info();
			case 4 -> update();
			case 5 -> logout();
			case 6 -> delete();
			case 7 -> backmenu();
			}

		} catch (Exception e) {
			join_menu();
		}
	}
	// TODO request

	private void request(int first_menu, int second_menu, JSONObject data) throws IOException {
		JSONObject request = new JSONObject();
		request.put("first_menu", first_menu);
		request.put("second_menu", second_menu);
		request.put("data", data);
		dos.writeUTF(request.toString());
		dos.flush();
	}

	// TODO respone

	private boolean response(int mn) throws IOException {
		JSONObject response = new JSONObject(dis.readUTF());
		if (response.getString("status").equals("ok")) {
			System.out.println("ok");
			join_menu();
			return true;
		}

		if (response.getString("status").equals("no") && mn == 1) {
			System.out.println("re Login");
			login();
			return true;
		}

		join_menu();
		return true;
	}

	// TODO JOIN
	private void insert() {
		try {
			System.out.print("id 입력: ");
			String input_id = br.readLine();

			System.out.print("name 입력: ");
			String input_name = br.readLine();

			System.out.print("password 입력: ");
			String input_password = br.readLine();

			System.out.print("email 입력: ");
			String input_email = br.readLine();

			JSONObject data = new JSONObject();
			data.put("id", input_id);
			data.put("name", input_name);
			data.put("pwd", input_password);
			data.put("email", input_email);

			request(1, 0, data);

			response(0);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void login() {
		try {

			System.out.print("ID: ");
			String login_id = br.readLine();

			System.out.print("Pwd: ");
			String login_pwd = br.readLine();

			JSONObject data = new JSONObject();
			data.put("login_id", login_id);
			data.put("login_pwd", login_pwd);

			request(1, 1, data);

			response(1);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void info() {
	}

	private void logout() {
	}

	private void update() {
	}

	private void delete() {
	}

	private void backmenu() {
		// TODO Auto-generated method stub
	}
}
