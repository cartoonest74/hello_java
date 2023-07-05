package boardTest.main;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import boardTest.board.BoardDAO;
import boardTest.board.BoardService;
import boardTest.extend.BoardSuper;
import boardTest.member.MemberDAO;
import boardTest.member.MemberService;
import boardTest.product.ProductDAO;
import boardTest.product.ProductService;

public class Client {
	private static Socket socket;
	private static DataOutputStream dos;
	private static DataInputStream dis;

	// BoardSuper 상속 class 객체
	private static BoardSuper memberDAO;
	private static BoardSuper productDAO;
	private static BoardSuper boardDAO;

	// service class
	private static MemberService memberService;
	private static ProductService productService;
	private static BoardService boardService;

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int loginIf = 0;
	private static String loginId = "";
	
	// loginId , loginIf 결합된 문자  
	private static String loginEN = "";

	public static void start() {
		try {
			socket = new Socket("localhost", 50001);
			System.out.println("client connection start!!");

			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());

			memberDAO = new MemberDAO(dos, dis);
			productDAO = new ProductDAO(dos, dis);
			boardDAO = new BoardDAO(dos, dis);

			memberService = new MemberService(dos, dis);
			productService = new ProductService(dos, dis);
			boardService = new BoardService(dos, dis);

		} catch (IOException e) {
			stop();
		}

		menu();
	}

	public static void stop() {
		try {
			br.close();
			socket.close();
			System.out.println("client close");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void menu() {
		String first_menu = new StringBuilder().append("---------------[ MENU ]------------------\n")
				.append("-----------------------------------------------------\n")
				.append("1.Member 2.Board 3.Product 4.Exit \n")
				.append("-----------------------------------------------------\n")
				.append("Select num: ").toString();
		System.out.println(first_menu);
		try {

			int select_num = Integer.parseInt(br.readLine());

			switch (select_num) {
			case 1 -> memberMenu(loginEN);
			case 2 -> boardMenu(loginId);
			case 3 -> productMenu(loginId);
			default -> {
				System.out.println("Bye Bye ~~~~~");
				stop();
			}
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();

		}
	}

	private static void menuFormat(String str_menu) {
		String menu_format = new StringBuilder().append("-----------------------------------------------------\n")
				.append(str_menu + "\n").append("-----------------------------------------------------\n")
				.append("SELECT NUM: ").toString();
		System.out.println(menu_format);
	}
	
	// memberMenu
	// memberMenu에서 login ExistsNoexists 값 받고 loginId , loginIf에 값 전달
	public static void memberMenu(String loginExistsNoexists) {
		loginEN = loginExistsNoexists.trim();
		
		if(! loginExistsNoexists.isEmpty() && loginEN.length() > 1) {
			String[] arr_loginEN = loginEN.trim().split(" ");
			
			loginId = arr_loginEN[0];
			loginIf = Integer.parseInt(arr_loginEN[1]);
		}
		
		switch (loginIf) {
			case 0 -> noLoginMode();
			case 1 -> loginMode();
		}
	}
	
	private static void loginMode() {

		String member_menu = new StringBuilder()
				.append("---------[ "+loginId+" logON "+"]---------- \n")
				.append("1.LOGOUT 2.MYINFO 3.SIGN_OUT 4.EXIT")
				.toString();
		menuFormat(member_menu);
		try {
			int select_num = Integer.parseInt(br.readLine());

			switch (select_num) {
			case 1 -> memberService.logout();
			case 2 -> memberService.myinfo();
			case 3 -> memberService.signOut();
			case 4 -> menu();
			}

		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();

		}
	}

	private static void noLoginMode() {
		String member_menu = "1.JOIN 2.LOGIN 3.EXIT";
		menuFormat(member_menu);
		try {
			int select_num = Integer.parseInt(br.readLine());

			switch (select_num) {
			case 1 -> memberService.join();
			case 2 -> memberService.login();
			case 3 -> menu();
			}

		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// boardMenu
	public static void boardMenu(String loginId) {
		switch (loginIf) {
		case 0 -> {
			System.out.println("Not Login.");
			menu();
		}
		case 1 -> {
			String board_menu = "1.BoardEnroll 2.BoardSelect 3.BoardEdit 4.BoardDelete 5.EXIT";
			menuFormat(board_menu);
			try {
				int select_num = Integer.parseInt(br.readLine());

				switch (select_num) {
				case 1 -> boardService.boardEnroll(loginId);
				case 2 -> boardService.boardSelect(loginId);
				case 3 -> boardService.boardEdit(loginId);
				case 4 -> boardService.boardDelete(loginId);
				case 5 -> menu();
				}

			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}

		}
		}
	}

	// productMenu
	public static void productMenu(String loginId) {
		switch (loginIf) {
		case 0 -> {
			System.out.println("Not Login.");
			menu();
		}
		case 1 -> {
			String product_menu = "1.ProductEnroll 2.ProdcutSelect 3.PrdouctEdit 4.ProductDelete 5.EXIT";
			menuFormat(product_menu);
			try {
				int select_num = Integer.parseInt(br.readLine());

				switch (select_num) {
				case 1 -> productService.productEnroll(loginId);
				case 2 -> productService.productSelect(loginId);
				case 3 -> productService.productEdit(loginId);
				case 4 -> productService.productDelete(loginId);
				case 5 -> menu();
				}

			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
	}

}