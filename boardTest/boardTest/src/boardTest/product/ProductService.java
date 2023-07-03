package boardTest.product;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import boardTest.exception.InputFail;
import boardTest.main.Client;

public class ProductService implements Service {
	private DataOutputStream dos;
	private DataInputStream dis;
	private BufferedReader br;
	private String inputInfo;
	private boolean inputInfoIf;

	private String tablename = "Product";
	private String className = "ProudctDAO";
	private ProductDAO productDAO;

	// product column
	private int nid;
	private String id;
	private String name;
	private String price;
	private String content;

	// 로그인 기록
	private int loginIf;
	private String loginId;

	public ProductService(DataOutputStream dos, DataInputStream dis) {
		super();
		this.dos = dos;
		this.dis = dis;
		productDAO = new ProductDAO(dos, dis);
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

	// 로그인 기록
	private JSONObject loginExists() throws IOException {
		JSONObject respone = new JSONObject(dis.readUTF());
		JSONObject login_data = respone.getJSONObject("data");

		loginId = login_data.getString("loginId");
		loginIf = login_data.getInt("loginIf");
		return respone;
	}
	
	// product select 해당 column value get
		private String productSelectData() throws IOException {
			String productSelectMsg;
			JSONObject respone = new JSONObject(dis.readUTF());
			JSONObject respone_data = respone.getJSONObject("data");
			JSONArray toRespone = respone_data.getJSONArray("classArray");
			String dataExistsMsg = "NO SEARCH DATA";
			
			if(toRespone.length() > 0) {

				for(int i = 0; i < toRespone.length(); i++) {
					JSONObject product = toRespone.getJSONObject(i);
					productSelectMsg = new StringBuilder()
							.append("--------------------------------------------\n")
							.append("NUM \t NID \t NAME \t PRICE \t CONTENT \n")
							.append(product.getInt("nid"))
							.append(". ")
							.append(product.getInt("nid"))
							.append(" \t ")
							.append(product.getString("id"))
							.append(" \t ")
							.append(product.getString("name"))
							.append(" \t ")
							.append(product.getString("price"))
							.append(" \t ")
							.append(product.getString("content"))
							.append(" \n")
							.append("--------------------------------------------")
							.toString();
					System.out.println(productSelectMsg);
				}
				return dataExistsMsg = "OK";
			}
			
			System.out.println(dataExistsMsg);
			return dataExistsMsg;
		}
	
	// member select 해당 column value get
	private JSONObject memberSelectData(JSONObject respone) {

		JSONObject respone_data = respone.getJSONObject("data");
		JSONArray toRespone = respone_data.getJSONArray("classArray");
		JSONObject select_data = toRespone.getJSONObject(0);

		return select_data;
	}

	@Override
	public void productEnroll(JSONObject loginJSON) {
		try {
			loginId = loginJSON.getString("loginId");

			System.out.println("--------- [ productEnroll ] ------------");

			// inputInfo empty check

			name = inputInfo("NAME");
			price = inputInfo("PRICE");
			content = inputInfo("CONTENT");

			JSONObject data = new JSONObject();
			data.put("id", loginId);
			
			int select = 2;
			request("Member", select, "MemberDAO", data);
			JSONObject respone = new JSONObject(dis.readUTF());
			JSONObject select_data = memberSelectData(respone);

			nid = select_data.getInt("nid");

			data.put("nid", nid);
			data.put("name", name);
			data.put("price", price);
			data.put("content", content);
			
			int insert = 1;
			request(tablename, insert, className, data);

			// 로그인 기록 가져오기
			JSONObject login_respone = loginExists();

			if (!login_respone.getString("status").equals("ok"))
				throw new InputFail("Input fail");

			Client.productMenu(login_respone);

		} catch (Exception e) {
		}

	}

	@Override
	public void productSelect(JSONObject loginJSON) {
		try {

			loginId = loginJSON.getString("loginId");

			JSONObject data = new JSONObject();
			data.put("id", loginId);
			
			int select = 2;
			request(tablename, select, className, data);

			// 로그인 기록 가져오기
			JSONObject login_respone = loginExists();

			if (!login_respone.getString("status").equals("ok"))
				throw new InputFail("input Fail!!");
			
			// prodcut 해당 id column data 
			if (login_respone.getString("status").equals("ok"))
				productSelectData();
			
			Client.productMenu(login_respone);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@Override
	public void productEdit(JSONObject loginJSON) {
		try {
			
			JSONObject login_respone = productSelectBasicForm(loginJSON);
			String editMsg  = new StringBuilder()
					.append("If you want to fix it, \n")
					.append("1.ok or 2.no?\n")
					.toString();
			System.out.println(editMsg);
			
			int okNoChoice = Integer.parseInt(inputInfo("NUM"));
			switch(okNoChoice) {
			case 1 ->{
				editProductInfo();
				Client.productMenu(login_respone);
			}
			case 2 -> Client.productMenu(login_respone);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private JSONObject productSelectBasicForm(JSONObject loginJSON) throws IOException {
		loginId = loginJSON.getString("loginId");
		
		System.out.println("--------- [ productEdit ] ------------");
		JSONObject data = new JSONObject();
		data.put("id", loginId);

		int select = 2;
		request(tablename, select, className, data);
		String select_ok = productSelectData();
		
		// 로그인 기록 가져오기
		JSONObject login_respone = loginExists();
		if(! select_ok.equals("ok")) {
			// 값이 없을시 product menu로 back
			Client.productMenu(login_respone);
		
		}
		return login_respone;
	}
	
	// product input editinfo
	private void editProductInfo() throws IOException, InputFail {
		JSONObject editData = new JSONObject();
		System.out.println("Please enter the board number to edit");
		int editNum= Integer.parseInt(inputInfo("Edit Product NUM")); 
		name = inputInfo("NAME");
		price = inputInfo("price");
		content = inputInfo("content");
		
		editData.put("num", editNum);
		editData.put("name", name);
		editData.put("price", price);
		editData.put("content", content);
		
		int update = 3;
		request(tablename, update, className, editData);
		
		JSONObject respone = new JSONObject(dis.readUTF());
		if (!respone.getString("status").equals("ok"))
			throw new InputFail("input Fail!!");
	}

	@Override
	public void productDelete(JSONObject loginJSON) {
		try {
			JSONObject login_respone = productSelectBasicForm(loginJSON);
			String delteMsg = new StringBuilder()
					.append("1. ALL Delte")
					.append(" \t ")
					.append("2. delete by num")
					.append(" \t ")
					.append("3. Back")
					.toString();
			System.out.println(delteMsg);
			int delChoice= Integer.parseInt(inputInfo("select Num"));
			
			int delete = 4;
			switch(delChoice) {
				case 1->{
					JSONObject data = new JSONObject();
					data.put("deleteALL",1);
					
					request(tablename, delete, className, data);
				}
				case 2->{
					int delNum = Integer.parseInt(inputInfo("Num: "));
					
					JSONObject data = new JSONObject();
					data.put("deleteALL",0);
					data.put("num", delNum);
					request(tablename, delete, className, data);
				}
				case 3-> Client.productMenu(login_respone);
			}
			
			JSONObject respone = new JSONObject(dis.readUTF());
			if (!respone.getString("status").equals("ok"))
				throw new InputFail("input Fail!!");
			
			Client.productMenu(login_respone);
			
		} catch (Exception e) {};

	}

}
