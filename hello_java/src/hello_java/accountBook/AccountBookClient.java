package hello_java.accountBook;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class AccountBookClient {
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	Scanner scanner;
	public void start() {
		try {
			socket = new Socket("localhost", 50001);
			System.out.println("client server connect!!!");
			
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			
			scanner = new Scanner(System.in);
			
			list();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		try {
			socket.close();
			scanner.close();
		} catch (IOException e) {
			System.out.println("client server Theend");
		}
	}
	
	public void menu() {
		String str_menual = new StringBuilder()
		.append("[상품목록]")
		.append("\n-----------------------------------------------------------------------------------------\n")
		.append("no\tname\t\t\t\t\tprice\t\t\tstock")
		.append("\n-----------------------------------------------------------------------------------------\n")
		.append("메뉴: 1.Create | 2.UPDATE | 3.Delete | 4.Exit")
		.append("\n선택:\t")
		.toString();
		System.out.println(str_menual);
		
		int choice_menu = scanner.nextInt();
		switch(choice_menu){
			case 1 -> create();
			case 2 -> update();
			case 3 -> delete();
			case 4 -> exit();
		}
	}
	
	public Object exit() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object delete() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object update() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object create() {
		// TODO Auto-generated method stub
		return null;
	}

	public void list() {
		// TODO Auto-generated method stub
		
	}
}
