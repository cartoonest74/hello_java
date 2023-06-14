package hello_java.accountBook;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.Socket;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class AccountBookClient {
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	private Scanner scanner;

	public void start() throws IOException {
		socket = new Socket("localhost", 50001);
		System.out.println("client server connect!!!");

		dos = new DataOutputStream(socket.getOutputStream());
		dis = new DataInputStream(socket.getInputStream());

		scanner = new Scanner(System.in);

		list();
	}

	public void stop() {
		try {
			socket.close();
			scanner.close();
		} catch (IOException e) {
			System.out.println("client server Theend");
		}
	}

	public void menu() throws IOException {
		String str_list = new StringBuilder().append("[상품목록]")
				.append("\n-----------------------------------------------------------------------------------------\n")
				.append("no\tname\t\t\tprice\t\tstock")
				.append("\n-----------------------------------------------------------------------------------------\n")
				.append("메뉴: 1.Create | 2.UPDATE | 3.Delete | 4.Exit").append("\n선택: ").toString();
		System.out.print(str_list);

		int choice_menu = scanner.nextInt();
		switch (choice_menu) {
		case 1 -> create();
		case 2 -> update();
		case 3 -> delete();
		case 4 -> exit();
		}
	}

	public void list() throws IOException {
		String str_menu = new StringBuilder().append("[상품목록]")
				.append("\n-----------------------------------------------------------------------------------------\n")
				.append("no\tname\t\t\tprice\t\tstock")
				.append("\n-----------------------------------------------------------------------------------------\n")
				.toString();
		System.out.println(str_menu);

		JSONObject request = new JSONObject();
		request.put("menu", 0);
		request.put("data", new JSONObject());
		dos.writeUTF(request.toString());
		dos.flush();

		JSONObject response = new JSONObject(dis.readUTF());
		if (response.getString("status").equals("ok")) {
			JSONArray data = response.getJSONArray("data");
			for (int i = 0; i < data.length(); i++) {
				JSONObject product = data.getJSONObject(i);
				String str_list = new StringBuilder().append(product.getInt("no")).append("\t")
						.append(product.getString("name")).append("\t\t\t").append(product.getInt("price"))
						.append("\t\t").append(product.getInt("stock")).toString();
				System.out.println(str_list);
			}
		}

		menu();

	}

	public void create() throws IOException {
		System.out.println("[상품생성]");
		Books books = new Books();

		scanner.nextLine();
		System.out.print("상품이름: ");
		String cName = scanner.nextLine();

		scanner.nextLine();
		System.out.print("상품가격: ");
		String cPrice = scanner.nextLine();

		scanner.nextLine();
		System.out.print("상품수량: ");
		String cStock = scanner.nextLine();

		books.setpName(cName);
		books.setpPrice(Integer.parseInt(cPrice));
		books.setpStock(Integer.parseInt(cStock));
		System.out.println(cStock);

		JSONObject data = new JSONObject();
		data.put("name", books.getpName());
		data.put("price", books.getpPrice());
		data.put("stock", books.getpStock());

		JSONObject request = new JSONObject();
		request.put("menu", "1");
		request.put("data", data);

		dos.writeUTF(request.toString());
		dos.flush();

		try {
			JSONObject response = new JSONObject(dis.readUTF());
			if (response.getString("status").equals("ok"))
				list();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public void exit(){
		// 상품 등록 내용 text파일로 만들어서 저장
		try {
			File file = new File("C:/Temp/accountBook.text");
			if(! file.exists()) {
				file.createNewFile();
//				file.setExecutable(true);
//				file.setReadable(true);
//				file.setWritable(true);
			}
			JSONObject request = new JSONObject();
			request.put("menu", 0);
			request.put("data",new JSONObject());
			dos.writeUTF(request.toString());
			dos.flush();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true)); 
			
			System.out.println("11111111");
			JSONObject response = new JSONObject(dis.readUTF());
			JSONArray books = response.getJSONArray("data");
			for(int i=0; i<books.length(); i++) {
				JSONObject product = books.getJSONObject(i);
				String str_list = new StringBuilder().append(product.getInt("no")).append("\t")
						.append(product.getString("name")).append("\t\t\t").append(product.getInt("price"))
						.append("\t\t").append(product.getInt("stock")).append("\n").toString();
				bw.write(str_list);
			}
			bw.flush();
			bw.close();
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		// client 종료
		stop();
	}

	public void delete() throws IOException {
		String str_list = new StringBuilder()
				.append("삭제할 상품 번호를 입력하시오\n")
				.append("상품번호: ").toString();
		System.out.print(str_list);
		int int_rp = scanner.nextInt();
		JSONObject data = new JSONObject();
		data.put("no", int_rp);

		JSONObject request = new JSONObject();
		request.put("menu", 3);
		request.put("data", data);
		dos.writeUTF(request.toString());
		dos.flush();

		JSONObject response = new JSONObject(dis.readUTF());
		if (response.getString("status").equals("ok"))
			list();
	}

	public void update() throws IOException {
		System.out.println("[상품수정]");
		Books books = new Books();

		System.out.print("상품번호입력: ");
		int uNo = scanner.nextInt();

		scanner.nextLine();
		System.out.print("상품이름: ");
		String uName = scanner.nextLine();
		books.setpName(uName);
		
		scanner.nextLine();
		System.out.print("상품가격: ");
		int uPrice = Integer.parseInt(scanner.nextLine());
		books.setpPrice(uPrice);

		scanner.nextLine();
		System.out.print("상품수량: ");
		int uStock = Integer.parseInt(scanner.nextLine());
		books.setpStock(uStock);
		
		
		JSONObject data = new JSONObject();
	
		data.put("no", books.getpNo());
		data.put("name", books.getpName());
		data.put("price", books.getpPrice());
		data.put("stock", books.getpStock());
		
		JSONObject request = new JSONObject();
		request.put("menu", 2);
		request.put("data", data);
		dos.writeUTF(request.toString());
		dos.flush();
		
		JSONObject response = new JSONObject(dis.readUTF());
		if (response.getString("status").equals("ok")) list();
	}

	public static void main(String[] arg) {
		AccountBookClient acbc = new AccountBookClient();
		try {
			acbc.start();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			acbc.stop();
		}
	}
}
