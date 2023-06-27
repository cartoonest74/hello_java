package Select.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONArray;
import org.json.JSONObject;

public class TestMemberService {
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	private BufferedReader br;
	public TestMemberService() {};
	public void start() throws UnknownHostException, IOException {
		socket = new Socket("localhost", 50001);
		System.out.println("client server connect");

		dos = new DataOutputStream(socket.getOutputStream());
		dis = new DataInputStream(socket.getInputStream());

		br = new BufferedReader(new InputStreamReader(System.in));
		
		list();

	}

	private void list() {
		try {
			JSONObject request = new JSONObject();
			request.put("menu",0);
			request.put("data",new JSONObject());
			dos.writeUTF(request.toString());
			dos.flush();

			JSONObject response = new JSONObject(dis.readUTF());
			if(response.getString("status").equals("ok")) {
				System.out.println("--------------------------------------------------\n");
				System.out.println("Id\tName\tTel\t\tAddr\tType\tEtc\n");
				JSONArray data = response.getJSONArray("data");
				for(int i = 0; i < data.length(); i++) {
					JSONObject members = data.getJSONObject(i);
					String all_list = new StringBuilder()
							.append(members.getString("id"))
							.append("\t")
							.append(members.getString("name"))
							.append("\t")
							.append(members.getString("tel"))
							.append("\t\t")
							.append(members.getString("addr"))
							.append("\t")
							.append(members.getInt("type"))
							.append("\t")
							.append(members.getString("etc"))
							.toString();
					System.out.println(all_list);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		
		}
		
		menu();
	}
	private void menu() {
		String str_menu = new StringBuilder().append("--------------------------------------------------\n")
				.append("Id\tName\tTel\t\tAddr\tType\tEtc\n")
				.append("--------------------------------------------------\n")
				.append("1.INSERT\t2.SELECT\t3.UPDATE\t4.DELETE\n5.EXIT\n")
				.append("--------------------------------------------------\n").append("SELECT NUM: ").toString();
		System.out.println(str_menu);
		int choice_num;
		try {
			choice_num = Integer.parseInt(br.readLine());
			
			switch (choice_num) {
			case 1 -> insert();
			case 2 -> select();
			case 3 -> update();
			case 4 -> delete();
			case 5 -> stop();
			}
		
		} catch (NumberFormatException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		
		}
	}
		
	private void insert() {
		try {
			System.out.print("id 입력: ");
			String input_id = br.readLine();

			System.out.print("name 입력: ");
			String input_name = br.readLine();
			
			System.out.print("tel 입력: ");
			String input_tel = br.readLine();
			
			System.out.print("addr 입력: ");
			String input_addr = br.readLine();
			
			System.out.print("type 입력: ");
			int input_type = Integer.parseInt(br.readLine());

			System.out.print("etc 입력: ");
			String input_etc = br.readLine();
			
			String input_char = "";
						
			switch(input_type) {
				case 1 -> {
					System.out.print("school 입력: ");
					input_char = br.readLine();
				}
				case 2 -> {
					System.out.print("dept 입력: ");
					input_char = br.readLine();
				}
				case 3 -> {
					System.out.print("job 입력: ");
					input_char = br.readLine();
				}
			}
			
			JSONObject data = new JSONObject();
			data.put("id", input_id);
			data.put("name", input_name);
			data.put("tel", input_tel);
			data.put("addr", input_addr);
			data.put("type", input_type);
			data.put("etc", input_etc);
			data.put("charater", input_char);
			
			JSONObject request = new JSONObject();
			request.put("menu", 1);
			request.put("data", data);
			dos.writeUTF(request.toString());
			dos.flush();
			
			JSONObject response = new JSONObject(dis.readUTF());
			if(response.getString("status").equals("ok")) {
				list();
			}
			if(! response.getString("status").equals("ok")) {
				System.out.println("NOT FOUND DATA");
				menu();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void select() {
	}
	private void update() {
	}
	private void delete() {
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
		TestMemberService testmemberService= new TestMemberService();
		try {
			testmemberService.start();
		} catch (IOException e) {
			testmemberService.stop();
		}
	}

}
