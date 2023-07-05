package boardTest.member;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import boardTest.exception.InputFail;
import boardTest.extend.BoardSuper;
import boardTest.main.Client;
import boardTest.server.ServerBT;

public class MemberService implements Service {
	private DataOutputStream dos;
	private DataInputStream dis;
	private BoardSuper memberDAO;
	private BufferedReader br;

	
	//
	private String inputInfo;
	private boolean inputInfoIf;
	private String inspect_id;
	private int nid;
	private String id;
	private String pwd;
	private String name;
	private String email;
	
	private String loginId;
	private int loginIf;

	public MemberService(DataOutputStream dos, DataInputStream dis) {
		super();
		this.dos = dos;
		this.dis = dis;
		memberDAO = new MemberDAO(dos, dis);
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	// inputInfo blankExists() 처리 및 blank일 경우 inputInfo 다시 호출
	private String inputInfo(String infoContent) throws IOException {
		System.out.print(infoContent + ": ");
		inputInfo = br.readLine();
		inputInfoIf = blankExists(inputInfo);
		if (inputInfoIf) {
			inputInfo(infoContent);
		}
		return inputInfo;
	}

	// parameter blankExists check
	private boolean blankExists(String strExists) {
		boolean blankIf = false;
		if (strExists.isBlank() && strExists == null) {
			System.out.println("no empty, Please enter again");
			blankIf = true;
		}
		return blankIf;
	}

	// id 중복 검사
	private String idDuplecheck() throws IOException {
		inspect_id = inputInfo("ID");
		JSONObject data = new JSONObject();
		data.put("id", inspect_id);
		data.put("tablename", "Member");
		
		int select = 2;
		request(select, data);

		// id 중복 여부 체크
		JSONObject respone = new JSONObject(dis.readUTF());
		int row = respone.getInt("row");
//		int idExists = memberDAO.select(request);
		if (row > 0) {
			System.out.println("Duplication ID!!, Please enter again");
			idDuplecheck();
		}

		return inspect_id;
	}

	private String idInspect() throws IOException {
		inspect_id = inputInfo("ID");
		JSONObject data = new JSONObject();
		data.put("id", inspect_id);
		data.put("tablename", "Member");
		
		int select = 2;
		request(select, data);

		// id값 존재여부
		JSONObject respone = new JSONObject(dis.readUTF());
		int row = respone.getInt("row");
		if (row < 1) {
			System.out.println("Noneexistent ID!!, Please enter again");
			idInspect();
		}
		return inspect_id;
	}

	@Override
	public void join() {
		try {
			System.out.println("--------- [ JOIN ] ------------");
			id = idDuplecheck();

			// id 중복 아닐 경우 값 입력
			// inputInfo empty check
			pwd = inputInfo("PWD");
			name = inputInfo("NAME");
			email = inputInfo("EMAIL");

			JSONObject data = new JSONObject();
			data.put("id", id);
			data.put("pwd", pwd);
			data.put("name", name);
			data.put("email", email);

			request(1, data);
			
			JSONObject respone = new JSONObject(dis.readUTF());
			JSONObject login_respone = loginExists(respone);
			
			if(! respone.getString("status").equals("ok"))
				throw new InputFail("Input fail");
			
			loginEN_ClinetToMemberMenu();
		
		} catch (Exception e) {}
	}

	// loginId, loginIf 결합해서 Client memberMenu 메소드로 값 전달 
	private void loginEN_ClinetToMemberMenu() throws IOException {	
		
		String loginExistsNoexists = new StringBuilder()
				.append(loginId)
				.append(" ")
				.append(loginIf)
				.toString().trim();
		
		Client.memberMenu(loginExistsNoexists);
	}
	
	// request 
	private void request(int menuNum, JSONObject data) throws IOException {
		data.put("tablename", "Member");
	
		JSONObject request = new JSONObject();
		request.put("menuNum", menuNum);
		request.put("className", "MemberDAO");
		request.put("data", data);
		dos.writeUTF(request.toString());
		dos.flush();
	}

	// 로그인 기록 
	private JSONObject loginExists(JSONObject respone) throws IOException {
		JSONObject login_data = respone.getJSONObject("data");
		
		loginId = login_data.getString("loginId");
		loginIf = login_data.getInt("loginIf");
		return respone;
	}

	// member select 해당 column value get
	private JSONObject memberSelectData(JSONObject respone) {
		
		JSONObject respone_data = respone.getJSONObject("data");
		JSONArray toRespone = respone_data.getJSONArray("classArray");
		JSONObject select_data = toRespone.getJSONObject(0);
		
		return select_data;
	}

	@Override
	public void myinfo() {
		try {
			System.out.println("--------- [ MYIFNO ] ------------");
			JSONObject data = new JSONObject();
			String edit_id = loginId.trim();
			data.put("id", edit_id);
			
			int select = 2;
			request(select, data);
			System.out.println("11111111111111");
			
			JSONObject member_respone = new JSONObject(dis.readUTF());
			JSONObject select_data = memberSelectData(member_respone);
			System.out.println("222222222222");
			
			String myInfos = new StringBuilder()
					.append("ID \t NAME \t EMAIL \n")
					.append(select_data.getString("id"))
					.append("\t")
					.append(select_data.getString("name"))
					.append("\t")
					.append(select_data.getString("email"))
					.toString();
			System.out.println(myInfos);
			
			String editMsg = new StringBuilder()
					.append("Would you like to edit? ") 
					.append("1.yes 2.no \n")
					.toString();
			System.out.print(editMsg);
			
			String editChoice = inputInfo("Select Num");
			
			if(editChoice.trim().equals("1")) {
					JSONObject request_data = editData(select_data);
					int update = 3;
					request(update, request_data);
					
					JSONObject edit_respone = new JSONObject(dis.readUTF());
					if(! edit_respone.getString("status").equals("ok"))
						throw new InputFail("Input fail");
			}
			
			loginEN_ClinetToMemberMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 
	private JSONObject editData(JSONObject select_data) throws IOException {
		System.out.println("----- Password validation -----");
		pwdValidatation(select_data);
		
		String editMsg = new StringBuilder()
				.append("Please enter your changes. Enter if nothing.\n")
				.append("1.Enter password only 2. All( Exclude id ) \n")
				.toString();
		System.out.print(editMsg);
		String editChoice = inputInfo("Select Num");

		JSONObject data = new JSONObject();

		pwd = inputInfo("PWD");
		data.put("pwd", pwd);
		data.put("changeOption", editChoice);

		if(editChoice.trim().equals("2")) {
				
				name = inputInfo("NAME");
				email = inputInfo("EMAIL");
				
				data.put("name", name);
				data.put("email", email);
				data.put("changeOption", editChoice);
		}

		nid = select_data.getInt("nid");
		data.put("nid", nid);
		return data;
	}

	private void pwdValidatation(JSONObject select_data) throws IOException {
//		System.out.println("----- Password validation -----");
		pwd = inputInfo("PWD");
		
		String compare_pwd = select_data.getString("pwd");
		
		if(! compare_pwd.equals(pwd)) {
			System.out.println("Passwords do not match!!. please enter again");
			pwdValidatation(select_data);
		}
	}

	@Override
	public void login() {
		System.out.println("--------- [ LOGIN ] ------------");
		try {
			id = idInspect().trim();
			pwd = inputInfo("PWD").trim();
			JSONObject data = new JSONObject();
			
			data.put("id", id);
			
			// id검사 및 pwd 가져오기
			int select = 2;
			request(select, data);
			
			JSONObject select_respone = new JSONObject(dis.readUTF());
			if(select_respone.getInt("row") < 1) {
				System.out.println("Wrong ID, pleas enter again");
				login();
			}
			
			JSONObject select_data = memberSelectData(select_respone);
			
			String compare_pwd = select_data.getString("pwd");
			
			if(select_respone.getInt("row") > 0 && ! compare_pwd.equals(pwd)) {
				System.out.println("Wrong PWD, please enter again");
				login();
			}
			
			nid = select_data.getInt("nid");
			data.put("nid", nid);
			
			int login = 0;
			request(login, data);
			
			JSONObject respone = new JSONObject(dis.readUTF());
			JSONObject login_respone = loginExists(respone);
			
			if(! respone.getString("status").equals("ok")) {
				throw new InputFail("Login fail");
				}
			
			loginEN_ClinetToMemberMenu();
			
		} catch (Exception e) {}
	}
	
	@Override
	public void logout() {
		try {
			int logout = 5;
			request(logout, new JSONObject());
			
			JSONObject respone = new JSONObject(dis.readUTF());
			JSONObject login_respone = loginExists(respone);
			
			if(! respone.getString("status").equals("ok")) {
				throw new InputFail("Logout fail");
				}
			
			String logoutMsg = new StringBuilder()
					.append("---------[ ")
					.append(loginId + " LOGOUT")
					.append(" ]------------\n")
					.toString();
			System.out.println(logoutMsg);
			
			
			loginEN_ClinetToMemberMenu();
			} catch (Exception e) {}
	}

	@Override
	public void signOut() {
		try {
			
			String sigOutmsg = new StringBuilder()
					.append("--------- [ SIGNOUT ] ------------\n")
					.toString();
			System.out.print(sigOutmsg);
			id = idInspect();
			JSONObject data = new JSONObject();
			data.put("id",id);
			
			int select = 2;
			request(select, data);
			
			JSONObject select_respone = new JSONObject(dis.readUTF());
			JSONObject select_data = memberSelectData(select_respone);
			pwdValidatation(select_data);
			
			if(loginId.equals(id)) {
				logout();
			}
			
			nid = select_data.getInt("nid");
			data.put("nid",nid);

			int delete = 4;
			request(delete, data);
			
			JSONObject respone = new JSONObject(dis.readUTF());
			JSONObject login_respone = loginExists(respone);
			System.out.println("Secession "+ id);
			
			if(! respone.getString("status").equals("ok")) {
				throw new InputFail("SignOut fail");
			}
			

			loginEN_ClinetToMemberMenu();
		} catch (Exception e) {}
	}
}