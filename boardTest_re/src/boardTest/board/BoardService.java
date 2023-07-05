package boardTest.board;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONObject;

import boardTest.exception.InputFail;
import boardTest.main.Client;

public class BoardService implements Service {
	private DataOutputStream dos;
	private DataInputStream dis;
	private BufferedReader br;
	private String inputInfo;
	private boolean inputInfoIf;

	private String tablename = "Board";
	private String className = "BoardDAO";
	private BoardDAO boardDAO;

	// Board column
	private int nid;
	private String id;
	private String writer;
	private String title;
	private String content;

	// 로그인 기록
	private String loginId;

	public BoardService(DataOutputStream dos, DataInputStream dis) {
		super();
		this.dos = dos;
		this.dis = dis;
		boardDAO = new BoardDAO(dos, dis);
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

	// request
	private void request(String tablename, int menuNum, String className, JSONObject data) throws IOException {
		data.put("tablename", tablename);

		JSONObject request = new JSONObject();
		request.put("menuNum", menuNum);
		request.put("className", className);
		request.put("data", data);
		dos.writeUTF(request.toString());
		dos.flush();
	}

	// member select 해당 column value get
	private JSONObject memberSelectData(JSONObject respone) {

		JSONObject respone_data = respone.getJSONObject("data");
		JSONArray toRespone = respone_data.getJSONArray("classArray");
		JSONObject select_data = toRespone.getJSONObject(0);

		return select_data;
	}

	// product select 해당 column value get
	private String boardSelectInfo(String loginId) throws IOException {

		this.loginId = loginId;

		JSONObject data = new JSONObject();
		data.put("id", loginId);

		int select = 2;
		request(tablename, select, className, data);

		JSONObject respone = new JSONObject(dis.readUTF());

		JSONObject respone_data = respone.getJSONObject("data");
		JSONArray toRespone = respone_data.getJSONArray("classArray");

		String productSelectMsg;
		String dataExistsMsg = "NO SEARCH DATA";

		if (toRespone.length() > 0) {

			for (int i = 0; i < toRespone.length(); i++) {
				JSONObject product = toRespone.getJSONObject(i);
				productSelectMsg = new StringBuilder().append("--------------------------------------------\n")
						.append("NUM \t NID \t ID \t WRITER \t TITLE \t CONTENT \n")
						.append(product.getInt("num"))
						.append(". ")
						.append(product.getInt("nid"))
						.append(" \t ")
						.append(product.getString("id"))
						.append(" \t ")
						.append(product.getString("writer"))
						.append(" \t ")
						.append(product.getString("title"))
						.append(" \t ")
						.append(product.getString("content"))
						.append(" \n")
						.append("--------------------------------------------")
						.toString();
				System.out.println(productSelectMsg);
			}

			return dataExistsMsg = "OK";
		}

		System.out.println("There is no product, please register");

		return dataExistsMsg;
	}

	@Override
	public void boardEnroll(String loginId) {
		try {
			this.loginId = loginId;

			System.out.println("--------- [ boardEnroll ] ------------");

			// inputInfo empty check

			JSONObject data = new JSONObject();
			data.put("id", loginId);
			
			int select = 2;
			request("Member", select, "MemberDAO", data);
			JSONObject member_respone = new JSONObject(dis.readUTF());
			JSONObject select_data = memberSelectData(member_respone);
			nid = select_data.getInt("nid");
			
			writer = inputInfo("WRITER");
			title = inputInfo("TITLE");
			content = inputInfo("CONTENT");

			data.put("nid", nid);
			data.put("writer", writer);
			data.put("title", title);
			data.put("content", content);
			
			int insert = 1;
			request(tablename, insert, className, data);

			JSONObject respone = new JSONObject(dis.readUTF());
			if (! respone.getString("status").trim().toLowerCase().equals("ok"))
				throw new InputFail("Input fail");

			Client.boardMenu(loginId);

		} catch (Exception e) {
		}
	}

	@Override
	public void boardSelect(String loginId) {
		try {
			boardSelectInfo(loginId);
			
			Client.boardMenu(loginId);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@Override
	public void boardEdit(String loginId) {
		try {
			System.out.println("--------- [ boardEdit ] ------------");
			
			String select_no = boardSelectInfo(loginId);
			
			// TODO ^^^^
			if(select_no.trim().toLowerCase().equals("ok")) {

				String editMsg  = new StringBuilder()
						.append("If you want to fix it, \n")
						.append("1.ok or 2.no?\n")
						.toString();
				System.out.println(editMsg);
				
				String okNoChoice = inputInfo("Select Num");
				
				if(okNoChoice.equals("1")) editBoardInfo();
			
			}
			
			Client.boardMenu(loginId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// product input editinfo
		private void editBoardInfo() throws IOException, InputFail {
			JSONObject editData = new JSONObject();
			System.out.println("Please enter the board number to edit");
			int editNum= Integer.parseInt(inputInfo("Edit Product NUM")); 
			writer = inputInfo("WRITER");
			title = inputInfo("TITLE");
			content = inputInfo("content");
			
			editData.put("num", editNum);
			editData.put("writer", writer);
			editData.put("title", title);
			editData.put("content", content);
			
			int update = 3;
			request(tablename, update, className, editData);
			
			JSONObject respone = new JSONObject(dis.readUTF());
			if (! respone.getString("status").equals("ok"))
				throw new InputFail("input Fail!!");
		}
	
	@Override
	public void boardDelete(String loginId) {
		try {
			String select_no =boardSelectInfo(loginId);
			
			if(select_no.trim().toLowerCase().equals("ok")) deleteBoardInfo();
			
			Client.boardMenu(loginId);
			
		} catch (Exception e) {};
	}
	
	// board delete info 및 전체 삭제 할지 상품번호로 삭제할지 choose
	
	private void deleteBoardInfo() throws IOException, InputFail {
		String delteMsg = new StringBuilder()
				.append("1. ALL Delte")
				.append(" \t ")
				.append("2. delete by num")
				.append(" \t ")
				.append("3. Back")
				.toString();
		System.out.println(delteMsg);
		String delChoice= inputInfo("Select Num").trim();
		
		int delete = 4;
		
		JSONObject data = new JSONObject();
		
		switch(delChoice) {
			case "1"->{
				data.put("deleteALL",1);
				request(tablename, delete, className, data);
			}
			case "2"->{
				System.out.println("Enter the bulletin board number to delete");
				int delNum = Integer.parseInt(inputInfo("Num").trim());
				
				data.put("deleteALL",0);
				data.put("num", delNum);
				request(tablename, delete, className, data);
			}
			default -> Client.boardMenu(loginId);
		}
		
		JSONObject respone = new JSONObject(dis.readUTF());
		if (! respone.getString("status").equals("ok"))
			throw new InputFail("input Fail!!");
	}

}
