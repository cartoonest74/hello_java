package memberBoard.boardMain;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.json.JSONArray;
import org.json.JSONObject;

import memberBoard.exception.CheckFormatException;

public class Menu {
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	private BufferedReader br;
	private int choice_num = 0;
	private JoinManagerMenu jmm;
	private BoardMenu bm;

	public Menu() {}

	public void start() throws IOException {
		socket = new Socket("localhost", 50002);
		System.out.println("client server connect");

		dos = new DataOutputStream(socket.getOutputStream());
		dis = new DataInputStream(socket.getInputStream());

		br = new BufferedReader(new InputStreamReader(System.in));

		 jmm = new JoinManagerMenu(socket, dos, dis);
		 bm = new BoardMenu(socket, dos, dis);

		menu();
	}

	private void menu() {
		String str_menu = new StringBuilder().append("--------------------------------------------------\n")
				.append("1.JOIN_Manager \t 2.Board \t 3.Exit\n")
				.append("--------------------------------------------------\n").append("SELECT NUM: ").toString();
		System.out.print(str_menu);
		try {
			choice_num = Integer.parseInt(br.readLine());
			switch (choice_num) {
			case 1 -> joinManger();
			case 2 -> board();
			case 3 -> exit();
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
		
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	private void joinManger() {
		jmm.join_menu();
	}

	private void board() {
		bm.borardmenu();
	}

	private void exit() {
		stop();
	}

	public void stop() {
		try {
			br.close();
			socket.close();
			System.out.println("client server TheEnd");
		} catch (IOException e) {
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu();
		try {
			menu.start();
		} catch (IOException e) {
			menu.stop();
		}
	}
}
