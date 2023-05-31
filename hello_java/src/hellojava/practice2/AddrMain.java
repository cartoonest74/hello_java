package hellojava.practice2;

import java.util.Scanner;

public class AddrMain {
	Scanner sc = new Scanner(System.in);
	String str_delete;
	int enrollCount = 0;
	void callInfo() {
		String infoMenu ="""
				1.등록
				2.삭제
				3.검색
				4.출력
				ex) 이외의 번호 입력시 종료
				번호 입력:
				""" ;
		System.out.println(infoMenu);
		int int_info = sc.nextInt();
		callinfo_if(int_info);
	}
	
	void callinfo_if(int int_info) {
		switch (int_info) {
			case 1:
				System.out.println("이름,나이,성별,전화번호,주소 순으로 입력해주세요");
				String str_enroll = sc.next();
				Addr gd = new Addr(str_enroll);
				enrollCount += 1;
			break;
			case 2 :
				if(enrollCount < 1) {
					System.out.println("삭제할 데이터가 없음");
				}else {
					str_delete = sc.next();
					enrollCount -= 1;
				}	
			break;
			case 3 :
				if(enrollCount < 1) {
					System.out.println("검색할 데이터가 없음");
				}else {
					String str_search = sc.next();
				}
			break;
			case 4 :
				if(enrollCount < 1) {
					System.out.println("출력할 데이터가 없음");
				}else {
					String str_output = sc.next();
				}
			break;
			default:
				System.out.println("없는 번호");
				break;
		}
	}
	
	public static void main(String[] arg) {
	}

}
