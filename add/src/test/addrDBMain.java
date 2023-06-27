package test;

import java.util.ArrayList;
import java.util.Scanner;

import service.ServiceImpl;
import vo.Member;

public class addrDBMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServiceImpl service = new ServiceImpl();
		boolean flag = true, flag2 = false;
		int menu, i;
		String name = null, tel = null, address = null;
		ArrayList<Member> list = null;
		Scanner sc = new Scanner(System.in);
		while (flag) {
			System.out.println("1.추가 2.전체검색 3.검색  4.종료 5.수정 6.삭제");
			menu = sc.nextInt();
			switch (menu) {
			case 1:
				System.out.print("이름:");
				name = sc.next();
				System.out.print("전화:");
				tel = sc.next();
				sc.nextLine();
				System.out.print("주소:");
				address = sc.nextLine();
				Member m = new Member(name, tel, address);
				service.addMember(m);
				break;
			case 2:
				list = service.getMembers();
				for (i = 0; i < list.size(); i++) {
					System.out.println(list.get(i));
				}
				break;
			case 3:
				System.out.print("검색할 사람의 이름:");
				name = sc.next();
				Member m2 = service.getMember(name);
				if (m2 == null) {
					System.out.println("검색한 사람이 없다.");
				} else {
					System.out.println(m2);
				}
				break;
			case 4:
				flag = false;
				break;
			case 5:
				System.out.print("수정할 사람의 이름:");
				name = sc.next();
				System.out.print("수정할 전화:");
				tel = sc.next();
				sc.nextLine();
				System.out.print("수정할 주소:");
				address = sc.nextLine();
				Member m3 = new Member(name, tel, address);
				flag2 = service.editMember(m3);
				if (flag2) {
					System.out.println("정상처리 되었습니다.");
				} else {
					System.out.println("처리되지 않았습니다.");
				}
				break;
			case 6:
				System.out.print("삭제할 사람의 이름:");
				name = sc.next();
				flag2 = service.delMember(name);
				if (flag2) {
					System.out.println("정상처리 되었습니다.");
				} else {
					System.out.println("처리되지 않았습니다.");
				}
				break;
			default:
				System.out.println("잘못입력");
			}
		}
		
		sc.close();
	}
}
