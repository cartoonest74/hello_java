package test;

import java.util.Scanner;

import service.AddresService;
import vo.addres;

public class TestService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		String name=null;
		String tel=null;
		String addr=null;
		boolean flag = true;
		int i = 0;
		AddresService  a = new AddresService();

		while (flag) {

			System.out.println("1.등록");
			System.out.println("2.검색");
			System.out.println("3.전체출력");
			System.out.println("4.수정");
			System.out.println("5.종료");
			
			i = sc.nextInt();
			switch (i) {
			case 1:
				System.out.println("이름을 입력하세요 :");
				name = sc.next();
				System.out.println("전화번호를 입력하세요 :");
				tel = sc.next();
				System.out.println("주소을 입력하세요 :");
				addr = sc.next();
		a.addPerson(new addres(name, tel, addr));
				break;
			case 2:
				System.out.println("이름을 입력하세요 :");
				name = sc.next();
				addres m = a.findPerson(name);

				System.out.println(m);
				break;
				

			case 3:
				a.printAll();
		        break; 
			
			case 4:
				System.out.println("이름을 입력하세요 :");
				name=sc.next(); 
				a.findUpdate(name);
		        break; 
			
			case 5:
				flag=false; 
			    break; 
			    
			default:
				System.out.println("잘못 입력했어요 다시 입력하세요 ");
			}

		}
		sc.close(); 
		}

	
	
	
	}


