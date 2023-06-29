package memberBoard.boardMain;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class BoardMenu {
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public BoardMenu(Socket socket, DataOutputStream dos, DataInputStream dis) {
		this.socket = socket;
		this.dos = dos;
		this.dis= dis;
	}
	
	public void borardmenu() {
		String board_menu = new StringBuilder().append("--------------------------------------------------\n")
				.append("Id\tName\tPassword\t\tEmail")
				.append("--------------------------------------------------\n")
				.append("1.JOIN\t2.LOGIN\t3.MYINFO\t4.EDIT\t5.LOGOUT\t6.DELETE\t7.BACK_MENU\n")
				.append("--------------------------------------------------\n").append("SELECT NUM: ").toString();
		System.out.println(board_menu);
		int choice_num;
		try {
			choice_num = Integer.parseInt(br.readLine());
			
			switch (choice_num) {
//			case 1 -> insert(); // join
//			case 2 -> login();
//			case 3 -> info();
//			case 4 -> update();
//			case 5 -> logout();
//			case 6 -> delete();
//			case 7 -> backmenu();
			}
		
			br.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		
		}
	}
	
}
