package addr_manager.main;

import java.util.Scanner;

import addr_manager.service.AddresService;
import addr_manager.vo.Addres;

public class AddrExample {
	// 등록 카운트
	static int enrollCount = 0;
	static AddresService usera = new AddresService();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		menual();
	}
	
	 static void menual() {
		String infoMenu ="""
				1.등록
				2.수정
				3.검색
				4.출력
				ex) 이외의 번호 입력시 종료
				번호 입력:
				""" ;
		System.out.println(infoMenu);
		Scanner sc = new Scanner(System.in);
		int int_info = sc.nextInt();
		callinfo_if(int_info);
	}
	
	 static void callinfo_if(int int_info) {
		Scanner sc = new Scanner(System.in);
		switch (int_info) {
			case 1:
				System.out.println("ID,이름,나이,성별,전화번호,주소 순으로 입력해주세요");
				String str_enroll = sc.next();
				String[] arr_str = str_enroll.trim().split(",");
				usera.enrollPerson(new Addres(arr_str[0], arr_str[1], arr_str[2], arr_str[3], arr_str[4], arr_str[5]));
				enrollCount ++;
				menual();
				break;
			case 2 :
				if(enrollCount < 1) {
					System.out.println("수정할 데이터가 없음");
				}else {
					System.out.println("수정할 아이디를 입력해주세요");
					String str_edit = sc.next();
					usera.userEdit(str_edit.trim());
				}	
				menual();
				break;
			case 3 :
				if(enrollCount < 1) {
					System.out.println("검색할 데이터가 없음");
				}else {
					System.out.println("검색 시작" +enrollCount);
					usera.searchUser(sc.next());
				}
				menual();
				break;
			case 4 :
				if(enrollCount < 1) {
					System.out.println("출력할 데이터가 없음");
				}else {
					usera.All_outPut();
				}
				menual();
				break;
			default:
				System.out.println("없는 번호 종료");
				break;
		}
	}
}
