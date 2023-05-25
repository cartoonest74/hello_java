package basic;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str_notice = """
				1. 등록
				2. 전체출력
				3. 종료
				""";
		String str_id= null, str_name = null, str_tele = null, str_regi = null, str_age = null;
		boolean flag = true;
//		String str_ss [][] = new String[][];
		String[] str_all = new String[5];
		int i = 0;
		while(flag) {
			System.out.println(" ");
			System.out.println(str_notice);
			Scanner sc = new Scanner(System.in);
			
			int int_choice = sc.nextInt();
			switch(int_choice){
				case 1:
					System.out.println("ID:");
					str_id = sc.next();
					
					System.out.println("이름:");
					str_name = sc.next();
					
					System.out.println("나이: ex) 20, 30");
					str_age = sc.next();
					
					System.out.println("전화번호");
					str_tele = sc.next();
					
					System.out.println("거주지");
					str_regi = sc.next();
					
					str_all[0] = str_id;
					str_all[1] = str_name;
					str_all[2] = str_age;
					str_all[3] = str_tele;
					str_all[4] =str_regi; 
					arr_regi.add(str_all[0]);
					i += 1;
					break;
				case 2:
					if(i != 0) {
						for(int i = 0; i<arr_regi.length; i++) {
							System.out.printf("ID: %s %n 이름: %s %n 나이: %d %n 전화번호: %s %n 거주지: %s %n%n", arr_regi[i][0], arr_regi[i][1], arr_regi[i][2], arr_regi[i][3], arr_regi[i][4]);													
						}
					}else {
						System.out.print("출력할 내용이 없습니다.");
					}
					break;
				default:
					flag = false;
					i = 0;
					System.out.print("종료");
					break;
			}
		}

	}

}
